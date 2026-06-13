package com.mealdbexplorer.backend.client;

import com.mealdbexplorer.backend.exception.ExternalApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

/**
 * Thin wrapper over TheMealDB's HTTP API. Returns the raw JSON response so the
 * service can cache it and the frontend can consume TheMealDB's schema directly.
 */
@Component
public class MealDbClient {

    private final RestClient restClient;

    public MealDbClient(@Value("${mealdb.base-url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public String searchByName(String name) {
        return get(uri -> uri.path("/search.php").queryParam("s", name).build());
    }

    public String randomMeal() {
        return get(uri -> uri.path("/random.php").build());
    }

    public String categories() {
        return get(uri -> uri.path("/categories.php").build());
    }

    public String lookupById(String id) {
        return get(uri -> uri.path("/lookup.php").queryParam("i", id).build());
    }

    public String filterByCategory(String category) {
        return get(uri -> uri.path("/filter.php").queryParam("c", category).build());
    }

    private String get(Function<UriBuilder, URI> uri) {
        try {
            return restClient.get().uri(uri).retrieve().body(String.class);
        } catch (RestClientException e) {
            throw new ExternalApiException("Failed to fetch data from TheMealDB");
        }
    }
}
