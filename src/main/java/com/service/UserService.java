package com.service;

import com.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    String encodePassword(String password);

    void createUser(User user);

    Optional<User> getUserByLogin(String login);

}
