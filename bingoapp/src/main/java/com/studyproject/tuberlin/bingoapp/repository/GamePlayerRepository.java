package com.studyproject.tuberlin.bingoapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.studyproject.tuberlin.bingoapp.entity.GamePlayer;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface GamePlayerRepository extends CrudRepository<GamePlayer, Long>{

	@Modifying
	@Transactional
	@Query("Delete from game_player gp where gp.gameId = ?1 and gp.playerId= ?2")
	public int deletePlayerFromGame(String gameId, String playerId);
	
	@Query("select gp from game_player gp where gp.gameId = :gameId ")
	  List<GamePlayer> getPlayersInSpecificGame(@Param("gameId") String gameId);
}
