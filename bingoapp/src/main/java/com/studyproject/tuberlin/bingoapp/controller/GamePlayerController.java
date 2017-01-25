package com.studyproject.tuberlin.bingoapp.controller;


import java.util.List;

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

import com.studyproject.tuberlin.bingoapp.entity.Game;
import com.studyproject.tuberlin.bingoapp.entity.GamePlayer;
import com.studyproject.tuberlin.bingoapp.helpers.DatabaseStatus;
import com.studyproject.tuberlin.bingoapp.helpers.GameStatus;
import com.studyproject.tuberlin.bingoapp.helpers.Lobby;
import com.studyproject.tuberlin.bingoapp.service.GamePlayerService;
import com.studyproject.tuberlin.bingoapp.service.GameService;

/**
 * This class is a controller for GamePlayer entity (Table)
 * @author sudarson
 *
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class GamePlayerController {
	
    GamePlayerService gamePlayerService;
    GameService gameService;
    
    @Autowired
   public GamePlayerController(GamePlayerService gamePlayerService, GameService gameService) {
		this.gamePlayerService = gamePlayerService;
		this.gameService = gameService;
	}
	
	@RequestMapping(value = "/gameplayer", method = RequestMethod.GET)
    public String sayHello(){
        return "Hello there !";
    }
	
	/**
	 * In Join game it will check whether the Game Started or not before Player Joins the Game
	 * It checks with the help of status (INACTIVE)
	 * If it is ACTIVE or COMPLETED it won't allow player to join the game
	 * This occurs in rare scenario when players selected "Join Game" and waited for long time to join a game where in the meantime other players started the Game.
	 * @param gamePlayer
	 * @return
	 */
	@RequestMapping(value = "/bingodb/game_player/add/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<GamePlayer> addPlayerToGame(@RequestBody GamePlayer gamePlayer){
		  Game game = gameService.findGame(gamePlayer.getGameId());
		  String status = game.getStatus();
		  GamePlayer gp = new GamePlayer();
		  if (status.equals(GameStatus.INACTIVE.toString())){
			  gp = gamePlayerService.savePlayer(gamePlayer); 
		  }
		  return new ResponseEntity<GamePlayer> (gp, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/bingodb/game_player/delete/", method = RequestMethod.POST)
		public @ResponseBody ResponseEntity<DatabaseStatus> deletePlayerFromGame(@RequestBody GamePlayer gamePlayer ){
			  int result = gamePlayerService.deletePlayerFromGame(gamePlayer.getGameId(), gamePlayer.getPlayerId());
			  DatabaseStatus status = new DatabaseStatus();
			  if(result == 1){
				  status.setMessage("Success");
			  }else{
				  status.setMessage("Error");
			  }
			  return new ResponseEntity<DatabaseStatus> (status, HttpStatus.OK);
		}
	 
	 /**
	  * Getting the Players list in a specific Game to show it in the Lobby
	  * Along with Current Status of the Game
	  * @param gameId
	  * @return
	  */
	 @RequestMapping(value = "/bingodb/lobby/{gameId}", method = RequestMethod.GET)
		public @ResponseBody ResponseEntity<Lobby> getPlayersInSpecificGame(@PathVariable String gameId){
		  List<GamePlayer> gp = gamePlayerService.getPlayersInSpecificGame(gameId);
		  Game game = gameService.findGame(gameId);
		  String status = game.getStatus();
		  Lobby lobby = new Lobby();
		  lobby.setGamePlayer(gp);
		  lobby.setGameStatus(status);
		  System.out.println("Lobby >>>>>>>>>>>>>>>>"+lobby);
		  return new ResponseEntity<Lobby> (lobby, HttpStatus.OK);
		}

}
