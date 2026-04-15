package com.lab7.lab7.controller;


import com.lab7.lab7.model.Film;
import com.lab7.lab7.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class FilmController {

    private final FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }


    @GetMapping
    public List<Film> getAll() {
        return service.getAll();
    }


    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        return service.addFilm(film);
    }


    @PutMapping("/{id}")
    public Film updateFilm(@PathVariable Long id, @RequestBody Film film) {
        return service.updateFilm(id, film);
    }


    @PatchMapping("/{id}/score")
    public Film updateScore(@PathVariable Long id, @RequestParam double score) {
        return service.updateScore(id, score);
    }


    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Long id) {
        service.deleteFilm(id);
    }
}