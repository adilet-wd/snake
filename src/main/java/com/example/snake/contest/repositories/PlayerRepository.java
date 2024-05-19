package com.example.snake.contest.repositories;

import com.example.snake.contest.models.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
