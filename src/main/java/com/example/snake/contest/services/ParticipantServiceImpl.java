package com.example.snake.contest.services;

import com.example.snake.contest.dto.CreateParticipantDTO;
import com.example.snake.contest.dto.UpdateParticipantDTO;
import com.example.snake.contest.models.Participant;
import com.example.snake.contest.models.Tournament;
import com.example.snake.contest.repositories.ParticipantRepository;
import com.example.snake.contest.repositories.TournamentRepository;
import com.example.snake.security.models.CustomUser;
import com.example.snake.security.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.*;

@Service
public class ParticipantServiceImpl implements ParticipantService{

    @Autowired
    private ParticipantRepository repository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private CustomUserRepository customUserRepository;

    //    Получение всех участников турнира
    @Transactional
    @Override
    public ResponseEntity<?> getParticipants(long tournamentId) {
        Map<String, String> response = new HashMap<>();

        // Проверка существования турнира с таким id
        Optional<Tournament> tournamentExist = tournamentRepository.findById(tournamentId);
        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tournamentExist.get().getActive_participants(), HttpStatus.OK);
    }

    // Получение участника турнира
    @Transactional
    @Override
    public ResponseEntity<?> getParticipant(long tournamentId, long id) {
        Map<String, String> response = new HashMap<>();

        // Проверка существования турнира с таким id
        Optional<Tournament> tournamentExist = tournamentRepository.findById(tournamentId);
        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Поиск участника с таким id
        Set<Participant> participants = tournamentExist.get().getActive_participants();
        Optional<Participant> participantExist = participants.stream()
                .filter(participant -> participant.getId() == id).findFirst();

        if(participantExist.isEmpty()){
            response.put("message", "Участник не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(participantExist.get(), HttpStatus.OK);
    }

    //    Создание участника турнира
    @Transactional
    @Override
    public ResponseEntity<?> saveParticipant(CreateParticipantDTO createParticipantDTO) {
        Map<String, String> response = new HashMap<>();

        // Проверка существования турнира с таким id
        Optional<Tournament> tournamentExist = tournamentRepository.findById(createParticipantDTO.getTournament_id());
        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Проверка, что турнир еще не начался
        LocalDate tournamentDate = tournamentExist.get().getDate().toLocalDate();
        if (tournamentDate.isBefore(LocalDate.now())) {
            response.put("message", "Турнир уже начался");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Проверка существования пользователя с таким id
        Optional<CustomUser> customUser = customUserRepository.findById(createParticipantDTO.getUser_id());
        if(customUser.isEmpty()){
            response.put("message", "Пользователь не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Проверка, что пользователь еще не участвует в турнире
        Optional<Participant> participantExist = repository.findByCustomUserIdAndTournamentId(createParticipantDTO.getUser_id(), createParticipantDTO.getTournament_id());
        if(participantExist.isPresent()){
            response.put("message", "Пользователь уже участвует в этом турнире");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Participant participant = convertToEntity(createParticipantDTO);
        repository.save(participant);
        return new ResponseEntity<>(participant, HttpStatus.CREATED);
    }

    //   Удаление участника турнира
    @Transactional
    @Override
    public ResponseEntity<?> deleteParticipant(long tournamentId, long id) {
        Map<String, String> response = new HashMap<>();

        // Проверка существования турнира с таким id
        Optional<Tournament> tournamentExist = tournamentRepository.findById(tournamentId);
        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        // Поиск участника с таким id
        Set<Participant> participants = tournamentExist.get().getActive_participants();
        Optional<Participant> participantExist = participants.stream()
                .filter(participant -> participant.getId() == id).findFirst();
        if(participantExist.isEmpty()){
            response.put("message", "Участник не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        repository.delete(participantExist.get());
        response.put("message", "Участник удален");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //    Изменение участника турнира
    @Transactional
    @Override
    public ResponseEntity<?> updateParticipant(UpdateParticipantDTO updateParticipantDTO, long tournamentId, long id) {
        Map<String, String> response = new HashMap<>();

        // Проверка существования турнира с таким id
        Optional<Tournament> tournamentExist = tournamentRepository.findById(tournamentId);
        if(tournamentExist.isEmpty()){
            response.put("message", "Турнир не найден");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        // Поиск участника с таким id
        Optional<Participant> participantExist = repository.findById(id);
        if(participantExist.isEmpty()){
            response.put("message", "Участник не найден");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        participantExist.get().setFunction(updateParticipantDTO.getFunction());

        return new ResponseEntity<>(repository.save(participantExist.get()), HttpStatus.OK);
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
