package com.model.repository;

import com.model.entity.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Long, Film> {
}
