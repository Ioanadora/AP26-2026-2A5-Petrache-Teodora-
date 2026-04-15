package com.lab7.tema7.lab7t.client;

import com.lab7.tema7.lab7t.model.Film;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FilmClient implements CommandLineRunner {

    private final RestTemplate restTemplate;
    private final String url = "http://localhost:8086/movies";

    public FilmClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) {
        runClient();
    }

    public void runClient() {


        Film film = new Film(null, "Client Film", "Action", 8.5);
        Film created = restTemplate.postForObject(url, film, Film.class);
        System.out.println("Created: " + created);

        Long id = created.getId();


        Film[] films = restTemplate.getForObject(url, Film[].class);
        System.out.println("All films: " + films.length);


        Film updated = new Film(id, "Updated Film", "Drama", 9.0);
        restTemplate.put(url + "/" + id, updated);


        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Film> response = restTemplate.exchange(
                url + "/" + id + "/score?score=10",
                HttpMethod.PATCH,
                entity,
                Film.class
        );

        System.out.println("Patched film: " + response.getBody());


        restTemplate.delete(url + "/" + id);

        System.out.println("Deleted film with id: " + id);
    }
}