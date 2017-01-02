package com.studyproject.tuberlin.bingoapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studyproject.tuberlin.bingoapp.entity.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, String>{

}
