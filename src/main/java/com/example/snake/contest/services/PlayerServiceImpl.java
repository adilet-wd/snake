package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreatePlayerDTO;
import com.example.snake.contest.models.Match;
import com.example.snake.contest.models.Player;
import com.example.snake.contest.models.Participant;
import com.example.snake.contest.repositories.MatchRepository;
import com.example.snake.contest.repositories.ParticipantRepository;
import com.example.snake.contest.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Transactional
    @Override
    public List<Player> getAllPlayers() {
        return (List<Player>) repository.findAll();
    }

    @Transactional
    @Override
    public Player getPlayer(long id) {
        return null;
    }

    @Transactional
    @Override
    public Player savePlayer(CreatePlayerDTO createPlayerDTO) {
        Player player = convertToEntity(createPlayerDTO);
        repository.save(player);
        return player;
    }

    @Transactional
    @Override
    public void deletePlayer(long id) {

    }

    @Transactional
    @Override
    public void updatePlayer(Player player) {

    }

    private Player convertToEntity(CreatePlayerDTO createPlayerDTO) {
        Player player = new Player();
        Long participantId = Long.valueOf(createPlayerDTO.getParticipant_id());
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow( ()-> new RuntimeException("Participant not found for id: "+createPlayerDTO.getParticipant_id()));
       player.setParticipant(participant);
        player.setUsername(participant.getUsername());
        return player;
    }
}
