package com.studyproject.tuberlin.bingoapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyproject.tuberlin.bingoapp.entity.Game;
import com.studyproject.tuberlin.bingoapp.helpers.GameStatus;
import com.studyproject.tuberlin.bingoapp.repository.GameRepository;

@Service
public class GameService {

	@Inject
    GameRepository gameRepository;
	
	/*@Autowired
	public GameService(GameRepository gameRepository){
		this.gameRepository = gameRepository;
	}*/
	GameService(){
		
	}
	
	public Game registerPlayer(Game game) {
		long millis = System.currentTimeMillis() + 5000000;
		game.setGameId(millis+"");
		game.setCallingNumbers(getShuffledNumbers());
		game.setCreationTime(System.currentTimeMillis()+"");
		game.setStatus(GameStatus.INACTIVE.toString());
		return gameRepository.save(game);
	}

	/**
	 * returning the shuffled numbers from 1 to 75 
	 * These numbers are the calling numbers (displayed in the screen to the players used to strike-out the numbers in their card)
	 * @return
	 */
	private String getShuffledNumbers() {
		String shuffledNumbers = null;
		List<Integer> dataList = new ArrayList<Integer>();
	    for (int i = 1; i <= 75; i++) {
	      dataList.add(i);
	    }
	    Collections.shuffle(dataList);
	    for (int i = 0; i < dataList.size(); i++) {
	    	shuffledNumbers = shuffledNumbers+"," + dataList.get(i);
	    }
	    int index = shuffledNumbers.indexOf(",");
	    shuffledNumbers = shuffledNumbers.substring(index+1, shuffledNumbers.length());
		return shuffledNumbers;
	}

	public List<Game> getInActiveGames() {
		return gameRepository.findGamebyStatus(GameStatus.INACTIVE.toString());
	}

	public int updateForSayBingo(String gameId, String ifBingo, String winner) {
		return gameRepository.updateForSayBingo(gameId, ifBingo, winner);
	}
	
	public int updateLongestMatch(String gameId, String longestMatch) {
		return gameRepository.updateLongestMatch(gameId, longestMatch);
	}

	public Game findGame(String gameId) {
		return gameRepository.findGame(gameId);
	}

	public int updateStatusCompleted(String gameId) {
		return gameRepository.updateStatus(gameId, GameStatus.COMPLETED.toString());
	}
	
	public int updateStatusActive(String gameId) {
		return gameRepository.updateStatus(gameId, GameStatus.ACTIVE.toString());
	}
	
	public boolean checkNewValueIsLongestMatch(String newLongestMatch, String oldLongestMatch) {
		int newValue = getValueFromLongestMatch(newLongestMatch);
		int oldValue = getValueFromLongestMatch(oldLongestMatch);
		if(newValue > oldValue){
			return true;
		}
		return false;
	}

	private int getValueFromLongestMatch(String longestMatch) {
		int value = -1;
		if(longestMatch != null && !longestMatch.equals("null")){
			int idx = longestMatch.indexOf(",");
			if(idx != -1){
				value = Integer.parseInt(longestMatch.substring(idx+1,longestMatch.length()));
			}
		}
		return value;
	}

}
