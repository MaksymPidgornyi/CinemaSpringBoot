package com.service;

import com.model.entity.Director;

public interface DirectorService {
    void addDirector(Director director);

    Iterable<Director> getAllDirectors();
}
