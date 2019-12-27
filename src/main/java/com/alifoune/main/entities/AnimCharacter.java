package com.alifoune.main.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "anim_character")
public class AnimCharacter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long idAnim;
	
	private String nameAnim;
	
	private String category;
	
	private String strength;
	
	private boolean shared;
	
	@Lob
	private byte[] photo;
	
	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	public AnimCharacter() {
		super();
	}

	public AnimCharacter(String nameAnim, String category, String strength, boolean shared, byte[] photo) {
		super();
		this.nameAnim = nameAnim;
		this.category = category;
		this.strength = strength;
		this.shared = shared;
		this.photo = photo;
	}

	
	public Long getIdAnim() {
		return idAnim;
	}

	public void setIdAnim(Long idAnim) {
		this.idAnim = idAnim;
	}

	public String getNameAnim() {
		return nameAnim;
	}

	public void setNameAnim(String nameAnim) {
		this.nameAnim = nameAnim;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
