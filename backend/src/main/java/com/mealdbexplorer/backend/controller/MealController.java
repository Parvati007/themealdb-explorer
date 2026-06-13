package com.mealdbexplorer.backend.controller;

import com.mealdbexplorer.backend.service.MealService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/meals", produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchMeal(@RequestParam String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Meal name cannot be empty");
        }
        return ResponseEntity.ok(mealService.searchMeal(name));
    }

    @GetMapping("/random")
    public ResponseEntity<String> randomMeal() {
        return ResponseEntity.ok(mealService.randomMeal());
    }

    @GetMapping("/categories")
    public ResponseEntity<String> categories() {
        return ResponseEntity.ok(mealService.categories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> mealById(@PathVariable String id) {
        return ResponseEntity.ok(mealService.mealById(id));
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<String> mealsByCategory(@PathVariable String name) {
        return ResponseEntity.ok(mealService.mealsByCategory(name));
    }
}
