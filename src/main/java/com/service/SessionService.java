package com.service;

import com.model.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface SessionService {
    void create(Session session);

    List<Session> getSessionsByDate(LocalDate date);

    Page<Session> getSessionsByDate(LocalDate date, Pageable pageable);
}
