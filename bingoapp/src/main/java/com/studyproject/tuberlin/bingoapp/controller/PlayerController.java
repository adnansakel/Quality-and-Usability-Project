package com.studyproject.tuberlin.bingoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studyproject.tuberlin.bingoapp.entity.Player;
import com.studyproject.tuberlin.bingoapp.service.PlayerService;

/**
 * This class is a controller for Player entity (Table)
 * @author sudarson
 *
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

	PlayerService playerService;
	
	@Autowired
	   public PlayerController(PlayerService playerService) {
			this.playerService = playerService;
		}
	
	@RequestMapping(value = "/bingodb/dummy/")
	public ResponseEntity<Player> get() {

	    Player player = new Player();
	    player.setEmail("ss@gmail.com");
	    player.setAge("56");
	    player.setGender("Male");
	    return new ResponseEntity<Player>(player, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bingodb/player/registration/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Player> registerPlayer(@RequestBody Player player){
		  Player p = playerService.registerPlayer(player);
		  return new ResponseEntity<Player> (p, HttpStatus.OK);
	}
	
}
