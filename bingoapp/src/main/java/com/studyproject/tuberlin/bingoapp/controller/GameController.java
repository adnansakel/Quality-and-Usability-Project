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

import com.studyproject.tuberlin.bingoapp.entity.Game;
import com.studyproject.tuberlin.bingoapp.service.GameService;

/**
 * This class is a controller for Game entity (Table)
 * @author sudarson
 *
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

	GameService gameService;
	
	@Autowired
	   public GameController(GameService gameService) {
			this.gameService = gameService;
		}
	
	@RequestMapping(value = "/bingodb/dummy2/")
	public ResponseEntity<Game> get() {

	    Game game = new Game();
	    game.setCreaterId("12345");
	    return new ResponseEntity<Game>(game, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bingodb/game/creation/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Game> gameCreation(@RequestBody Game game){
		  Game p = gameService.registerPlayer(game);
		  return new ResponseEntity<Game> (p, HttpStatus.OK);
	}
	
}
