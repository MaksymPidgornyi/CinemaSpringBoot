package com.service;

import com.model.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private SessionRepository repository;

    @Autowired
    public void setRepository(SessionRepository repository) {
        this.repository = repository;
    }
}
