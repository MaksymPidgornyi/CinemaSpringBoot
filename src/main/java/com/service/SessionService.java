package com.service;

import com.model.entity.Session;
import com.model.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SessionService {
    private SessionRepository repository;

    @Autowired
    public void setRepository(SessionRepository repository) {
        this.repository = repository;
    }

    public void create(Session session){
        repository.save(session);
    }

    public Page<Session> getSessionsByDate(LocalDate date, Pageable pageable){
        return repository.findAllBySessionDate(date, pageable);
    }
}
