package com.controller;

import com.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@Controller
@RequestMapping("/afisha")
public class SessionsController {
    private SessionService sessionService;

    @Autowired
    public void setSessionRepository(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public String getSessions(@RequestParam(name = "date", required = false) String date, Model model, Pageable pageable) {

        LocalDate sessionsDate = Objects.isNull(date) ? LocalDate.now() : LocalDate.parse(date);

        model.addAttribute("sessions", sessionService.getSessionsByDate(sessionsDate, pageable));
        model.addAttribute("sessionsDate", sessionsDate);

        return "afisha";
    }
}
