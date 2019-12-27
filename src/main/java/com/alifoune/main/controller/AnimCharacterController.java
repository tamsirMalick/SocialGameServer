package com.alifoune.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alifoune.main.entities.AnimCharacter;
import com.alifoune.main.repository.AnimCharacterRepository;

@RestController
@RequestMapping("/v1/animes")
public class AnimCharacterController {

	@Autowired
	private AnimCharacterRepository animCharacterRepository;

	@GetMapping("/")
	public ResponseEntity findAll() {
		return ResponseEntity.ok(animCharacterRepository.findAll());
	}

	@GetMapping("/{idAnim}")
	public ResponseEntity findAnimeById(@PathVariable("idAnim") Long idAnim) {
		if (idAnim == null) {
			return ResponseEntity.badRequest().body("can not find anime by empty ID");
		}

		AnimCharacter anime = animCharacterRepository.getOne(idAnim);

		if (anime == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(anime);
	}

	@PostMapping("/add")
	public ResponseEntity createAnime(@RequestBody AnimCharacter anime) {
		if (anime == null) {
			return ResponseEntity.badRequest().body("can not create empty anime");
		}

		return ResponseEntity.ok(animCharacterRepository.save(anime));
	}

	@DeleteMapping("/{idAnim}")
	public ResponseEntity deleteAnime(@PathVariable("idAnim") Long idAnim) {
		if (idAnim == null) {
			return ResponseEntity.badRequest().body("can not remove anime with null ID");
		}

		AnimCharacter anime = animCharacterRepository.getOne(idAnim);
		if (anime == null) {
			return ResponseEntity.notFound().build();
		}
		animCharacterRepository.delete(anime);
		return ResponseEntity.ok("Anime remove with success");
	}

	@GetMapping("/share/{idAnim}/{isShared}")
	public ResponseEntity shareAnime(@PathVariable(name = "idAnim") Long idAnim, @PathVariable(name = "isShared") boolean isShared) {
		if (idAnim == null) {
			return ResponseEntity.badRequest().body("Can not share anime with empty ID");
		}
		AnimCharacter anime = animCharacterRepository.getOne(idAnim);
		if (anime == null) {
			return ResponseEntity.notFound().build();
		}
		anime.setShared(isShared);
		
		return ResponseEntity.ok(animCharacterRepository.save(anime));
	}
}
