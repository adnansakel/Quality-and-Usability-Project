package com.studyproject.tuberlin.bingoapp.helpers;

import java.util.List;

import com.studyproject.tuberlin.bingoapp.entity.GamePlayer;

public class Lobby {

	private List<GamePlayer> gamePlayer;
	private String gameStatus;

	public Lobby(){

	}

	public Lobby(List<GamePlayer> gamePlayer, String gameStatus) {
		super();
		this.gamePlayer = gamePlayer;
		this.gameStatus = gameStatus;
	}

	public List<GamePlayer> getGamePlayer() {
		return gamePlayer;
	}

	public void setGamePlayer(List<GamePlayer> gamePlayer) {
		this.gamePlayer = gamePlayer;
	}

	public String getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	@Override
	public String toString() {
		return "Lobby [gamePlayer=" + gamePlayer + ", gameStatus=" + gameStatus + "]";
	}
}
