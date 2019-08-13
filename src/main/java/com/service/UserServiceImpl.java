package com.service;

import com.model.entity.User;
import com.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository rep;
    private MessageSource messageSource;
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository rep, MessageSource messageSource, PasswordEncoder encoder) {
        this.rep = rep;
        this.messageSource = messageSource;
        this.encoder = encoder;
    }

    @Override
    public String encodePassword(String password){
        return encoder.encode(password);
    }

    @Override
    public void createUser(User user){
        rep.save(user);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return Optional.of(rep.findByLogin(login));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return rep.findByLogin(s);
    }
}
