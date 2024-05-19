package com.example.snake.contest.repositories;

import com.example.snake.contest.models.Tournament;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TournamentRepository extends CrudRepository<Tournament, Long>{
    Optional<Tournament> findByName(String name);
}
