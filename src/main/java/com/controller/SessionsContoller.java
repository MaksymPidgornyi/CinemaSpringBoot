package com.controller;

import com.model.entity.Session;
import com.model.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/afisha")
public class SessionsContoller {
    private SessionRepository sessionRepository;

    @Autowired
    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping
    public String getSessions(@RequestParam(name="date", required = false) String dateParam, Model model){

        LocalDate date = Optional.of(LocalDate.parse(dateParam)).orElse(LocalDate.now());
        List<Session> sessionsByDate = sessionRepository.findAllBySessionDate(date);
        model.addAttribute("sessions", sessionsByDate);

        return "afisha";
    }
}
