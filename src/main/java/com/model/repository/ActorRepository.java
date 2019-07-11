package com.model.repository;

import com.model.entity.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Long, Actor> {
}
