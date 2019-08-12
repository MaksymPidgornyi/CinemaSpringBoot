package com.service;

import com.model.entity.Film;
import com.model.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private FilmRepository repository;

    @Autowired
    public void setRepository(FilmRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Film> getAllMovies(){
        return repository.findAll();
    }

    @Override
    public void createMovie(Film film){
        repository.save(film);
    }

    @Override
    public void deleteMovie(Film film){
        repository.delete(film);
    }
}
