package com.example.snake.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Обработчик web-запросов
 */
@RestController
public class WebController {
    @GetMapping("/user")
    public String User(@RequestParam (value = "name", defaultValue = "You are logined") String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/admin")
    public String Admin(@RequestParam (value = "name", defaultValue = "You are Admin") String name) {
        return String.format("Hello, %s!", name);
    }

}