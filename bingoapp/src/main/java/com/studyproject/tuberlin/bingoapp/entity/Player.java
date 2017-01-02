package com.studyproject.tuberlin.bingoapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO Class for Player Table
 * This class stores the Players information
 * @author sudarson
 *
 */

@Entity(name = "player")
@Table(name = "player")
public class Player {
	
	@Id
	@Column(name = "playerid", nullable = false)
    private String playerId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "age")
	private String age;
	
	public Player(){	
	}
	
	public Player(String playerId, String name, String email, String gender, String age){
		this.name = name;
		this.playerId = playerId;
		this.email = email;
		this.gender = gender;
		this.age = age;	
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", name=" + name + ", email=" + email + ", gender=" + gender + ", age="
				+ age + "]";
	}

}
