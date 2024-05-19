package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateParticipantDTO;
import com.example.snake.contest.models.Participant;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.repositories.ParticipantRepository;
import com.example.snake.contest.repositories.TournamentRepository;
import com.example.snake.security.models.CustomUser;
import com.example.snake.security.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService{

    @Autowired
    private ParticipantRepository repository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private CustomUserRepository customUserRepository;

    @Transactional
    @Override
    public List<Participant> getParticipants() {
        return (List<Participant>) repository.findAll();
    }

    @Transactional
    @Override
    public Participant getParticipant(long id) {
        return null;
    }

    @Transactional
    @Override
    public Participant saveParticipant(CreateParticipantDTO createParticipantDTO) {
        Participant participant = convertToEntity(createParticipantDTO);
        repository.save(participant);
        return participant;
    }

    @Transactional
    @Override
    public void deleteParticipant(long id) {

    }

    @Transactional
    @Override
    public void updateParticipant(Participant participant) {

    }


    private Participant convertToEntity(CreateParticipantDTO createParticipantDTO) {
        Participant participant = new Participant();
        participant.setFunction(createParticipantDTO.getFunction());

        //        Получение турнира по его ID и создание связи
        Tournament tournament = tournamentRepository.findById(createParticipantDTO.getTournament_id())
                .orElseThrow(() -> new RuntimeException("Tournament not found with id " + createParticipantDTO.getTournament_id()));
        participant.setTournament(tournament);

        //        Получение юзера по его ID  создание связи
        CustomUser user = customUserRepository.findById(createParticipantDTO.getUser_id())
                .orElseThrow( () -> new RuntimeException("User not found with id" + createParticipantDTO.getUser_id()) );
        participant.setCustomUser(user);
        participant.setUsername(user.getUsername());

        return participant;
    }
}
