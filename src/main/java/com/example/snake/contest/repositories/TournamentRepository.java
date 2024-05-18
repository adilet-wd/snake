package com.example.snake.contest.repositories;

import com.example.snake.contest.models.Tournament;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TournamentRepository extends CrudRepository<Tournament, Long>{

}
