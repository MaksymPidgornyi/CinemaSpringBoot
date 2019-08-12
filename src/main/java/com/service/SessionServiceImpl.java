package com.service;

import com.model.entity.Session;
import com.model.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private SessionRepository repository;

    @Autowired
    public void setRepository(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Session session){
        repository.save(session);
    }

    @Override
    public List<Session> getSessionsByDate(LocalDate date){
        return repository.findAllBySessionDate(date);
    }

    @Override
    public Page<Session> getSessionsByDate(LocalDate date, Pageable pageable){
        return repository.findAllBySessionDate(date, pageable);
    }

    public void deleteSession(Session session){
        repository.delete(session);
    }
}
