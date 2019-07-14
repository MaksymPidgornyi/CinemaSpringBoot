package com.service;

import com.model.entity.Actor;
import com.model.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {
    private ActorRepository repository;

    @Autowired
    public void setRepository(ActorRepository repository) {
        this.repository = repository;
    }

    public void addActor(Actor actor){
        repository.save(actor);
    }

    public Iterable<Actor> getAllActors(){
        return repository.findAll();
    }
}
