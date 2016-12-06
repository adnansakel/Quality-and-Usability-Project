package com.studyproject.tuberlin.bingoapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; 

/**
 * POJO Class for GamePlayer Table
 * This class stores the Players (playerId) for a specific game (gameId)
 * @author sudarson
 *
 */
@Entity(name = "game_player")
@Table(name = "game_player")
public class GamePlayer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

	@Column(name = "gameid", nullable = false)
	private String gameId;
	@Column(name = "playerid", nullable = false)
	private String playerId;
	
	public GamePlayer(){	
	}
	
	public GamePlayer(String gameId, String playerId){
		this.gameId = gameId;
		this.playerId = playerId;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getGameId() {
		return gameId;
	}
	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	public String getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
}
