package com.studyproject.tuberlin.bingoapp.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyproject.tuberlin.bingoapp.entity.Player;
import com.studyproject.tuberlin.bingoapp.repository.PlayerRepository;

@Service
public class PlayerService {

	@Inject
    PlayerRepository playerRepository;
	
	/*@Autowired
	public PlayerService(PlayerRepository playerRepository){
		this.playerRepository = playerRepository;
	}*/
	PlayerService(){
		
	}
	
	public Player registerPlayer(Player player) {
		long millis = System.currentTimeMillis() + 10000;
		player.setPlayerId(millis+"");
		return playerRepository.save(player);
	}

}
