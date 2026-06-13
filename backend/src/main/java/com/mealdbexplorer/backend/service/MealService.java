package com.mealdbexplorer.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealdbexplorer.backend.client.MealDbClient;
import com.mealdbexplorer.backend.exception.ExternalApiException;
import com.mealdbexplorer.backend.exception.ResourceNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MealService {

    private final MealDbClient client;
    private final ObjectMapper objectMapper;

    public MealService(MealDbClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Cacheable("mealSearch")
    public String searchMeal(String name) {
        return client.searchByName(name);
    }

    // Not cached: a random meal must stay random on every request.
    public String randomMeal() {
        return client.randomMeal();
    }

    @Cacheable("categories")
    public String categories() {
        return client.categories();
    }

    @Cacheable("mealsByCategory")
    public String mealsByCategory(String category) {
        return client.filterByCategory(category);
    }

    @Cacheable("mealDetails")
    public String mealById(String id) {
        String response = client.lookupById(id);
        if (hasNoMeals(response)) {
            throw new ResourceNotFoundException("No meal found with id: " + id);
        }
        return response;
    }

    // TheMealDB returns {"meals": null} when nothing matches a lookup.
    private boolean hasNoMeals(String json) {
        try {
            return objectMapper.readTree(json).path("meals").isNull();
        } catch (Exception e) {
            throw new ExternalApiException("Invalid response from TheMealDB");
        }
    }
}
