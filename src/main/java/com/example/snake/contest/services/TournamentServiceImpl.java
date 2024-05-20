package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateTournamentDTO;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.repositories.TournamentRepository;
import com.example.snake.security.models.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository repository;

    @Transactional
    @Override
    public List<Tournament> getTournaments() {
        return (List<Tournament>) repository.findAll();
    }

    // Получение турнира по id
    @Transactional
    @Override
    public ResponseEntity<?> getTournamentById(long id) {
        Optional<Tournament> tournamentExist = repository.findById(id);

        if(tournamentExist.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tournamentExist, HttpStatus.OK);

    }

    // Получение турнира по названию
    @Transactional
    @Override
    public ResponseEntity<?> getTournamentByName(String name) {

        Optional<Tournament> tournamentExist = repository.findByName(name);;

        if(tournamentExist.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tournamentExist, HttpStatus.OK);
    }

    //  Создание турнира
    @Transactional
    @Override
    public ResponseEntity<?> saveTournament(CreateTournamentDTO createTournamentDTO) {

        // Проверка существования турнира с таким названием

        Optional<Tournament> tournamentExist = repository.findByName(createTournamentDTO.getName());

        if(tournamentExist.isPresent()){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Турнир с таким названием уже существует");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Tournament tournament = convertToEntity(createTournamentDTO);
        repository.save(tournament);

        return new ResponseEntity<>(tournament, HttpStatus.CREATED);
    }

    //  Удаление турнира по id
    @Transactional
    @Override
    public ResponseEntity<?> deleteTournament(long id) {
        Map<String, String> response = new HashMap<>();

        // Проверка существования турнира с таким названием

        Optional<Tournament> tournamentExist = repository.findById(id);
        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        repository.delete(tournamentExist.get());
        response.put("message", "Турнир удален");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //    Превращение DTO в Entity
    private Tournament convertToEntity(CreateTournamentDTO createTournamentDTO) {
        Tournament tournament = new Tournament();
        tournament.setName(createTournamentDTO.getName());
        tournament.setDate(createTournamentDTO.getDate());
        tournament.setDescription(createTournamentDTO.getDescription());
        return tournament;
    }

}
