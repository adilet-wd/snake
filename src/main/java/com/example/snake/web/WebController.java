package com.example.snake.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Обработчик web-запросов
 */
@RestController
public class WebController {
    @GetMapping("/hello")
    public String Hello(@RequestParam (value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/user")
    public String User(@RequestParam (value = "name", defaultValue = "You are logined") String name) {
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/admin")
    public String Admin(@RequestParam (value = "name", defaultValue = "You are Admin") String name) {
        return String.format("Hello, %s!", name);
    }

}