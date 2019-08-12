package com.service;

import com.model.entity.Director;
import com.model.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {
    private DirectorRepository repository;

    @Autowired
    public void setRepository(DirectorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addDirector(Director director){
        repository.save(director);
    }

    @Override
    public Iterable<Director> getAllDirectors(){
        return repository.findAll();
    }
}
