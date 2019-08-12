package com.service;

import com.model.entity.User;

public interface UserService {
    String encodePassword(String password);

    void createUser(User user);
}
