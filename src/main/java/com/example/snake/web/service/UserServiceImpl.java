package com.example.snake.web.service;

import com.example.snake.web.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public List<User> get() {
        return List.of();
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
