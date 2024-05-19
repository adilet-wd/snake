package com.example.snake.contest.repositories;

import com.example.snake.contest.models.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long>    {
}
