package com.studyproject.tuberlin.bingoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.studyproject.tuberlin.bingoapp.entity.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, String>{

	@Query("select g from game g where g.status = :status ")
	  List<Game> findGamebyStatus(@Param("status") String status);
	
	@Query("select g from game g where g.gameId = :gameId ")
	  Game findGame(@Param("gameId") String gameId);
	
	@Modifying
	@Transactional
	@Query("update game g set g.ifBingo = ?2, g.winner = ?3 where g.gameId = ?1")
	public int updateForSayBingo(String gameId, String ifBingo, String winner);
	
	@Modifying
	@Transactional
	@Query("update game g set g.longestMatch = ?2 where g.gameId = ?1")
	public int updateLongestMatch(String gameId, String longestMatch);
	
	@Modifying
	@Transactional
	@Query("update game g set g.status = ?2 where g.gameId = ?1")
	public int updateStatus(String gameId, String status);
	
	@Modifying
	@Transactional
	@Query("Delete from game")
	public int deleteGameData();
}
