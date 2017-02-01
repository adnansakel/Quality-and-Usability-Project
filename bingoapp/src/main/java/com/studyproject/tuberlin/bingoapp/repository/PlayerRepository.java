package com.studyproject.tuberlin.bingoapp.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.studyproject.tuberlin.bingoapp.entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, String>{

	@Query("select p.profilePicture from player p where p.playerId = :playerId ")
	  Player getPlayerProfilePicture(@Param("playerId") String playerId);
	
	@Modifying
	@Transactional
	@Query("Delete from player")
	public int deletePlayerData();
}
