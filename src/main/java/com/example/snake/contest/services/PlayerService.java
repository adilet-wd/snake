package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreatePlayerDTO;
import com.example.snake.contest.models.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    Player getPlayer(long id);
    Player savePlayer(CreatePlayerDTO createPlayerDTO);
    void deletePlayer(long id);
    void updatePlayer(Player player);
}
