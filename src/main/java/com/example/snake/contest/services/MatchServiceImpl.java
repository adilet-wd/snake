package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateMatchDTO;
import com.example.snake.contest.dto.CreatePlayerDTO;
import com.example.snake.contest.models.Match;
import com.example.snake.contest.models.Player;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.snake.contest.repositories.MatchRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MatchServiceImpl implements MatchService{

    @Autowired
    private MatchRepository repository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    TournamentRepository tournamentRepository;

    @Transactional
    @Override
    public List<Match> getAllMatches() {
        return (List<Match>) repository.findAll();
    }

    @Transactional
    @Override
    public Match getMatch(long id) {
        return null;
    }

    @Transactional
    @Override
    public Match saveMatch(CreateMatchDTO createMatchDTO) {
        Match match = convertToEntity(createMatchDTO);
        repository.save(match);
        return match;
    }

    @Transactional
    @Override
    public void deleteMatch(long id) {

    }

    @Transactional
    @Override
    public void updateMatch(Match match) {

    }

//    Создание матчей и игроков в нем по ДТО
    private Match convertToEntity(CreateMatchDTO createMatchDTO) {

        Match match = new Match();
        Tournament tournament = tournamentRepository.findById(createMatchDTO.getTournament_id())
                .orElseThrow(() -> new RuntimeException("Tournament not found with id " + createMatchDTO.getTournament_id()));
        match.setTournament(tournament);

        CreatePlayerDTO player1 = new CreatePlayerDTO();
        CreatePlayerDTO player2 = new CreatePlayerDTO();

        player1.setParticipant_id(createMatchDTO.getParticipant1_id());
        player2.setParticipant_id(createMatchDTO.getParticipant2_id());

        Player player1entity = playerService.savePlayer(player1);
        Player player2entity = playerService.savePlayer(player2);

        Set<Player> players = new HashSet<>();
        players.add(player1entity);
        players.add(player2entity);

        match.setPlayers(players);

        return match;
    }
}
