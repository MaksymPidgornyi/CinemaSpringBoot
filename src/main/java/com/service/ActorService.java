package com.service;

import com.model.entity.Actor;

import java.util.List;

public interface ActorService {
    void addActor(Actor actor);
    List<Actor> getAllActors();
}
