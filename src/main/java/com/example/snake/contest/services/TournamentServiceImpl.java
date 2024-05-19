package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateTournamentDTO;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository repository;

    @Transactional
    @Override
    public List<Tournament> getTournaments() {
        return (List<Tournament>) repository.findAll();
    }

    @Transactional
    @Override
    public Tournament getTournament(long id) {
        return null;
    }

    @Transactional
    @Override
    public Tournament saveTournament(CreateTournamentDTO createTournamentDTO) {
        Tournament tournament = convertToEntity(createTournamentDTO);
        repository.save(tournament);
        return tournament;
    }

    private Tournament convertToEntity(CreateTournamentDTO createTournamentDTO) {
        Tournament tournament = new Tournament();
        tournament.setName(createTournamentDTO.getName());
        tournament.setDate(createTournamentDTO.getDate());
        return tournament;
    }


    @Transactional
    @Override
    public void deleteTournament(long id) {

    }

    @Transactional
    @Override
    public void updateTournament(Tournament tournament) {

    }
}
