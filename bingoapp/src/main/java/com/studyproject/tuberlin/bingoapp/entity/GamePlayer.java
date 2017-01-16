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
	@Column(name = "player_name")
	private String playerName;
	
	public GamePlayer(){	
	}
	
	public GamePlayer(String gameId, String playerId, String playerName){
		this.gameId = gameId;
		this.playerId = playerId;
		this.playerName = playerName;
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

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String toString() {
		return "GamePlayer [id=" + id + ", gameId=" + gameId + ", playerId=" + playerId + ", playerName=" + playerName
				+ "]";
	}
}
