package com.mealdbexplorer.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")// Health-check endpoint to verify the API is running
    public String home() {
        return "TheMealDB Explorer API is running successfully";
    }
}