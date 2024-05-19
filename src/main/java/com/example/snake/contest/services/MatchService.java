package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateMatchDTO;
import com.example.snake.contest.models.Match;

import java.util.List;

public interface MatchService {
    List<Match> getAllMatches();
    Match getMatch(long id);
    Match saveMatch(CreateMatchDTO createMatchDTO);
    void deleteMatch(long id);
    void updateMatch(Match match);
}
