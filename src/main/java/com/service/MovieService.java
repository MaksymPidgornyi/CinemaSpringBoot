package com.service;

import com.model.entity.Film;

public interface MovieService {
    Iterable<Film> getAllMovies();

    void createMovie(Film film);

    void deleteMovie(Film film);
}
