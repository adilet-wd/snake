package com.example.snake.security.controllers;

import com.example.snake.contest.dto.CreateParticipantDTO;
import com.example.snake.contest.models.Participant;
import com.example.snake.contest.models.Tournament;
import com.example.snake.security.dto.CreateCustomUserDTO;
import com.example.snake.security.models.CustomUser;
import com.example.snake.security.repositories.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserRepository customUserRepository;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateCustomUserDTO createCustomUserDTO) {

        //       Проверка на существующего пользователя
        CustomUser existingUserByEmail = customUserRepository.findByEmail(createCustomUserDTO.getEmail()).orElse(null);
        CustomUser existingUserByUsername = customUserRepository.findByUsername(createCustomUserDTO.getUsername()).orElse(null);

        List<String> errors = new ArrayList<>();

        if (existingUserByEmail != null) {
            errors.add("Пользователь с этой почтой уже зарегистрирован.");
        }
        if (existingUserByUsername != null) {
            errors.add("Пользователь с этим именем уже зарегистрирован.");
        }
        if (!createCustomUserDTO.getEmail().endsWith("gmail.com")){
            errors.add("Эта почта не действительна");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        CustomUser user = convertToEntity(createCustomUserDTO);
        user.setPassword(passwordEncoder.encode(createCustomUserDTO.getPassword()));
        return ResponseEntity.ok(customUserRepository.save(user));
    }

    @GetMapping("/users")
    public List<CustomUser> getUsers() {
        return customUserRepository.findAll();
    }

//    Преобразование DTO в Entity
     private CustomUser convertToEntity(CreateCustomUserDTO createCustomUserDTO) {
        CustomUser user = new CustomUser();
        user.setUsername(createCustomUserDTO.getUsername());
        user.setEmail(createCustomUserDTO.getEmail());
        user.setPassword(createCustomUserDTO.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return user;
    }

}


