package com.example.snake.contest.controllers;


import com.example.snake.contest.dto.CreatePlayerDTO;
import com.example.snake.contest.models.Player;
import com.example.snake.contest.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

//    @Autowired
//    private PlayerService playerService;
//
//    @GetMapping("/players")
//    public List<Player> get() {
//        return playerService.getAllPlayers();
//    }
}
