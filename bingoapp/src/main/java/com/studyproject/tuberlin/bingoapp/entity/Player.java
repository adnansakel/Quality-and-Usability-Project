package com.studyproject.tuberlin.bingoapp.entity;

import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	
	@Column(name = "player_name", nullable = false)
	private String playerName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "age")
	private String age;
	
	// We mark up the byte array with a long object datatype, setting the fetch type to lazy.
	  @Lob
	  @Basic(fetch=FetchType.LAZY) // this gets ignored anyway, but it is recommended for blobs
	  @Column(name = "profile_picture")
	  protected  byte[] profilePicture;
 
	
	public Player(){	
	}
	
	public Player(String playerId, String playerName, String email, String gender, String age, byte[] profilePicture) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.profilePicture = profilePicture;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerName=" + playerName + ", email=" + email + ", gender=" + gender
				+ ", age=" + age + ", profilePicture=" + Arrays.toString(profilePicture) + "]";
	}
}
