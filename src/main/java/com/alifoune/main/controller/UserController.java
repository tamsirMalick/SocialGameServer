package com.alifoune.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alifoune.main.entities.User;
import com.alifoune.main.repository.UserRepository;

@RestController
@RequestMapping("/v1/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public ResponseEntity findAll() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/{idUser}")
	public ResponseEntity findUserById(@PathVariable(name = "idUser") Long idUser) {
		if (idUser == null) {
			return ResponseEntity.badRequest().body("can not retrieve with null ID");
		}
		User user = userRepository.getOne(idUser);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/add")
	public ResponseEntity createUser(@RequestBody User user) {
		if (user == null) {
			return ResponseEntity.badRequest().body("can not create a null user");
		}

		User createdUser = userRepository.save(user);
		return ResponseEntity.ok(createdUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
		if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			return ResponseEntity.badRequest().body("can not login with empty user or empty password");
		}
		
		User authenticatedUser = userRepository.findByEmailAndPassword(email, password);
		if(authenticatedUser == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(authenticatedUser);
		
	}
}
