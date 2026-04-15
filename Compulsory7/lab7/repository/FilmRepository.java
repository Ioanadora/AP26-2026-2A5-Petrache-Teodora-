package com.lab7.lab7.repository;

import com.lab7.lab7.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
