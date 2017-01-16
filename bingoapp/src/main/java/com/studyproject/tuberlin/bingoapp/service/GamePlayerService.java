package com.studyproject.tuberlin.bingoapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyproject.tuberlin.bingoapp.entity.GamePlayer;
import com.studyproject.tuberlin.bingoapp.repository.GamePlayerRepository;

/**
 * This class is the service class for the GamePlayerRepository
 * @author sudarson
 *
 */
@Service
public class GamePlayerService {
	@Inject
    GamePlayerRepository gamePlayerRepository;
	
	/*@Autowired
	public GamePlayerService(GamePlayerRepository gamePlayerRepository){
		this.gamePlayerRepository = gamePlayerRepository;
	}*/
	
	public GamePlayer savePlayer(GamePlayer gamePlayer){
		return gamePlayerRepository.save(gamePlayer);
	}
	
	public int deletePlayerFromGame(String gameId, String playerId){
		return gamePlayerRepository.deletePlayerFromGame(gameId, playerId);
	}
	
	public List<GamePlayer> getPlayersInSpecificGame(String gameId) {
		return gamePlayerRepository.getPlayersInSpecificGame(gameId);
	}
	
}
