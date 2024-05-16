package com.example.snake.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Обработчик web-запросов
 */
@RestController
public class WebController {
    @GetMapping("/hello")
    public String User(@RequestParam (value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}