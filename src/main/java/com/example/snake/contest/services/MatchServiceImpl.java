package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateMatchDTO;
import com.example.snake.contest.dto.CreatePlayerDTO;
import com.example.snake.contest.models.Match;
import com.example.snake.contest.models.Participant;
import com.example.snake.contest.models.Player;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.repositories.TournamentRepository;
import com.example.snake.security.models.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.snake.contest.repositories.MatchRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class MatchServiceImpl implements MatchService{

    @Autowired
    private MatchRepository repository;

    @Autowired
    private PlayerService playerService;

    @Autowired
    TournamentRepository tournamentRepository;

    //    Получение всех матчей турнира
    @Transactional
    @Override
    public ResponseEntity<?> getMatches(Long tournamentId) {
        Map<String, String> response = new HashMap<>();

        // Проверка существования турнира с таким id
        Optional<Tournament> tournamentExist = tournamentRepository.findById(tournamentId);
        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tournamentExist.get().getMatches(), HttpStatus.OK);
    }

    //    Получение матча турнира
    @Transactional
    @Override
    public ResponseEntity<?> getMatch(long tournamentId, long id) {
        Map<String, String> response = new HashMap<>();

        // Проверка существования турнира с таким id
        Optional<Tournament> tournamentExist = tournamentRepository.findById(tournamentId);
        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Поиск матча с таким id
        Set<Match> matches = tournamentExist.get().getMatches();
        Optional<Match> matchExist = matches.stream()
                .filter(match -> match.getId() == id).findFirst();

        if(matchExist.isEmpty()){
            response.put("message", "Матч не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(matchExist.get(), HttpStatus.OK);

    }

    //   Создание матча
    @Transactional
    @Override
    public ResponseEntity<?> saveMatch(CreateMatchDTO createMatchDTO) {
        Map<String, String> response = new HashMap<>();

        // Проверка чтобы в матче были разные пользователи
        if (createMatchDTO.getParticipant1_id().equals(createMatchDTO.getParticipant2_id())) {
            response.put("message", "Участники должны быть разные");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Проверка существования турнира с таким id
        Optional<Tournament> tournamentExist = tournamentRepository.findById(createMatchDTO.getTournament_id());
        Integer participant1Id = createMatchDTO.getParticipant1_id();
        Integer participant2Id = createMatchDTO.getParticipant2_id();

        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Проверка существования участников в турнире
        Set<Participant> participants = tournamentExist.get().getActive_participants();
        Optional<Participant> participant1 = participants.stream()
                .filter(p -> Objects.equals(p.getId(), participant1Id))
                .findFirst();
        Optional<Participant> participant2 = participants.stream()
                .filter(p -> Objects.equals(p.getId(), participant2Id))
                .findFirst();

        if(participant1.isEmpty() || participant2.isEmpty()){
            response.put("message", "Участник не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Match match = convertToEntity(createMatchDTO);
        repository.save(match);
        return new ResponseEntity<>(match, HttpStatus.CREATED);

    }

    // Удаление матча
    @Transactional
    @Override
    public ResponseEntity<?> deleteMatch(long tournamentId, long id) {
        Map<String, String> response = new HashMap<>();

        // Проверка существования турнира с таким id
        Optional<Tournament> tournamentExist = tournamentRepository.findById(tournamentId);
        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Поиск матча с таким id
        Set<Match> matches = tournamentExist.get().getMatches();
        Optional<Match> matchExist = matches.stream()
                .filter(match -> match.getId() == id).findFirst();

        if(matchExist.isEmpty()){
            response.put("message", "Матч не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        repository.delete(matchExist.get());
        response.put("message", "Матч удален");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Transactional
    @Override
    public void updateMatch(Match match) {

    }

    // Создание матчей и игроков в нем по ДТО
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
