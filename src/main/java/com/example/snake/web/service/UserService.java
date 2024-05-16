package com.example.snake.web.service;

import com.example.snake.web.model.User;

import java.util.List;

public interface UserService {
    List<User> get();
    User get(int id);
    void save(User user);
    void delete(int id);
}
