package com.service;

import com.model.entity.Director;
import com.model.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    private DirectorRepository repository;

    @Autowired
    public void setRepository(DirectorRepository repository) {
        this.repository = repository;
    }

    public void addDirector(Director director){
        repository.save(director);
    }

    public Iterable<Director> getAllDirectors(){
        return repository.findAll();
    }
}
