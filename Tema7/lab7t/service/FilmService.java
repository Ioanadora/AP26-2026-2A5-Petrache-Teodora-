package com.lab7.tema7.lab7t.service;

import com.lab7.tema7.lab7t.model.Film;
import com.lab7.tema7.lab7t.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository repository;

    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }

    public List<Film> getAll() {
        return repository.findAll();
    }

    public Film addFilm(Film film) {
        return repository.save(film);
    }

    public Film updateFilm(Long id, Film updated) {
        Film film = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        film.setTitle(updated.getTitle());
        film.setGenre(updated.getGenre());
        film.setScore(updated.getScore());

        return repository.save(film);
    }

    public Film updateScore(Long id, double score) {
        Film film = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        film.setScore(score);
        return repository.save(film);
    }

    public void deleteFilm(Long id) {
        repository.deleteById(id);
    }
}