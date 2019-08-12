package com.service;

import com.model.entity.Actor;
import com.model.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {
    private ActorRepository repository;

    @Autowired
    public void setRepository(ActorRepository repository) {
        this.repository = repository;
    }

    public void addActor(Actor actor){
        repository.save(actor);
    }

    public List<Actor> getAllActors(){
        return repository.findAll();
    }
}
