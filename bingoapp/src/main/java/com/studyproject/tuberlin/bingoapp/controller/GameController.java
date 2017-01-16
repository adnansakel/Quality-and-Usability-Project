package com.studyproject.tuberlin.bingoapp.controller;

import java.util.List;

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
	
	/*@RequestMapping(value = "/bingodb/dummy2/")
	public ResponseEntity<Game> get() {

	    Game game = new Game();
	    game.setCreatorId("12345");
	    return new ResponseEntity<Game>(game, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/bingodb/game/creation/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Game> gameCreation(@RequestBody Game game){
		  Game p = gameService.registerPlayer(game);
		  return new ResponseEntity<Game> (p, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bingodb/gamelist_to_join/", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Game>> getInActiveGames(){
		  List<Game> g = gameService.getInActiveGames();
		  return new ResponseEntity<List<Game>> (g, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bingodb/saybingo/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Game> sayBingo(@RequestBody Game game){
		  String ifBingo = game.getIfBingo();
		  String gameId = game.getGameId();
		  String winner = game.getWinner();
		  System.out.println("Result >>>>>>>>>>"+ifBingo+" ,,,, "+winner);
		  gameService.updateForSayBingo(gameId, ifBingo, winner);
		  Game p = gameService.findGame(gameId);
		  gameService.updateStatusCompleted(gameId);
		  return new ResponseEntity<Game> (p, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bingodb/ifbingo/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Game> ifBingo(@RequestBody Game game){
		  String newLongestMatch = game.getLongestMatch();
		  String gameId = game.getGameId();
		  Game p = gameService.findGame(gameId);
		  String oldLongestMatch = p.getLongestMatch();
		  boolean result = gameService.checkNewValueIsLongestMatch(newLongestMatch, oldLongestMatch);
		  if(result){
			  gameService.updateLongestMatch(gameId, newLongestMatch);
		  }
		  p = gameService.findGame(gameId);
		  return new ResponseEntity<Game> (p, HttpStatus.OK);
	}
	
}
