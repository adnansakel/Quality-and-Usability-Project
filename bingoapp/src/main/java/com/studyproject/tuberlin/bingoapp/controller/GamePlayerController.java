package com.studyproject.tuberlin.bingoapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.studyproject.tuberlin.bingoapp.entity.GamePlayer;

import com.studyproject.tuberlin.bingoapp.service.GamePlayerService;

/**
 * This class is a controller for GamePlayer entity (Table)
 * @author sudarson
 *
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class GamePlayerController {
	
    GamePlayerService gamePlayerService;
    
    @Autowired
   public GamePlayerController(GamePlayerService gamePlayerService) {
		this.gamePlayerService = gamePlayerService;
	}
	
	@RequestMapping(value = "/gameplayer", method = RequestMethod.GET)
    public String sayHello(){
        return "Hello there !";
    }
	
	@RequestMapping(value = "/bingodb/game_player/add/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<GamePlayer> addPlayerToGame(@RequestBody GamePlayer gamePlayer){
		  GamePlayer gp = gamePlayerService.savePlayer(gamePlayer);
		  return new ResponseEntity<GamePlayer> (gp, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/delete/gameplayer/{gameId}/{playerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public int deletePlayerFromGame(@PathVariable String gameId, @PathVariable String playerId){
			 return gamePlayerService.deletePlayerFromGame(gameId, playerId);
		}

}
