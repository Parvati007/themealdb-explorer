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

    @GetMapping("/search")// Search meals by name
    public ResponseEntity<String> searchMeal(@RequestParam String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Meal name cannot be empty");
        }
        return ResponseEntity.ok(mealService.searchMeal(name));
    }

    @GetMapping("/random")// Fetch a random meal from TheMealDB API
    public ResponseEntity<String> randomMeal() {
        return ResponseEntity.ok(mealService.randomMeal());
    }

    @GetMapping("/categories")// Retrieve all available meal categories
    public ResponseEntity<String> categories() {
        return ResponseEntity.ok(mealService.categories());
    }

    @GetMapping("/{id}")// Get meal details using meal ID
    public ResponseEntity<String> mealById(@PathVariable String id) {
        return ResponseEntity.ok(mealService.mealById(id));
    }

    @GetMapping("/category/{name}")// Get meals belonging to a specific category
    public ResponseEntity<String> mealsByCategory(@PathVariable String name) {
        return ResponseEntity.ok(mealService.mealsByCategory(name));
    }
}
