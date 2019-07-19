package com.service;

import com.model.entity.User;
import com.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository rep;
    private MessageSource messageSource;
    private PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository rep, MessageSource messageSource, PasswordEncoder encoder) {
        this.rep = rep;
        this.messageSource = messageSource;
        this.encoder = encoder;
    }

    public String encodePassword(String password){
        return encoder.encode(password);
    }

    public void createUser(User user){
        rep.save(user);
    }
}
