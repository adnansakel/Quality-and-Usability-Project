package com.studyproject.tuberlin.bingoapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO Class for Game Table
 * This class stores the Game information
 * @author sudarson
 *
 */

@Entity(name = "game")
@Table(name = "game")
public class Game {
	
	@Id
	@Column(name = "gameid", nullable = false)
    private String gameId;
	
	@Column(name = "calling_numbers", nullable = false)
	private String callingNumbers;
	
	@Column(name = "if_bingo")
	private String ifBingo;
	
	@Column(name = "winner")
	private String winner;
	
	@Column(name = "longest_match")
	private String longestMatch;
	
	@Column(name = "creatorid", nullable = false)
    private String creatorId;
	
	@Column(name = "creation_time")
    private String creationTime;
	
	@Column(name = "status")
    private String status;
	
	@Column(name = "creator_name")
	private String creatorName;
	
	public Game(){	
	}

	public Game(String gameId, String callingNumbers, String ifBingo, String winner, String longestMatch,
			String creatorId, String creationTime, String status, String creatorName) {
		super();
		this.gameId = gameId;
		this.callingNumbers = callingNumbers;
		this.ifBingo = ifBingo;
		this.winner = winner;
		this.longestMatch = longestMatch;
		this.creatorId = creatorId;
		this.creationTime = creationTime;
		this.status = status;
		this.creatorName = creatorName;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getCallingNumbers() {
		return callingNumbers;
	}

	public void setCallingNumbers(String callingNumbers) {
		this.callingNumbers = callingNumbers;
	}

	public String getIfBingo() {
		return ifBingo;
	}

	public void setIfBingo(String ifBingo) {
		this.ifBingo = ifBingo;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getLongestMatch() {
		return longestMatch;
	}

	public void setLongestMatch(String longestMatch) {
		this.longestMatch = longestMatch;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", callingNumbers=" + callingNumbers + ", ifBingo=" + ifBingo + ", winner="
				+ winner + ", longestMatch=" + longestMatch + ", creatorId=" + creatorId + ", creationTime="
				+ creationTime + ", status=" + status + ", creatorName=" + creatorName + "]";
	}
}
