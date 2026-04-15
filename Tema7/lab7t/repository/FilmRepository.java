package com.lab7.tema7.lab7t.repository;

import com.lab7.tema7.lab7t.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
