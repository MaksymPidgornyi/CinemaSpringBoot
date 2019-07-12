package com.service;

import com.model.dto.UserDto;
import com.model.entity.Role;
import com.model.entity.User;
import com.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository rep;
    private MessageSource messageSource;

    @Autowired
    public UserService(UserRepository rep, MessageSource messageSource) {
        this.rep = rep;
        this.messageSource = messageSource;
    }

    public User dtoToUser(UserDto dto){
        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setRole(Role.USER);
        user.setPassword(dto.getPassword());
        user.setActivity(false);

        return user;
    }

    public void createUser(User user){
        rep.save(user);
    }

    public boolean isDtoValid(UserDto dto){
        return false;
    }
}
