package com.model.repository;

import com.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Long, User> {
}
