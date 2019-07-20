package com.controller;

import com.model.entity.Session;
import com.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Map;
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
        model.addAttribute("maxSessionStartTime", LocalTime.of(22, 0));

        return "afisha";
    }

    @PostMapping("/session")
    public String postSession(@Valid Session session, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrorsMap(bindingResult);

            model.mergeAttributes(errors);
        }
        else {

            LocalTime start = session.getStartTime();
            Duration movieDuration = session.getFilm().getDuration();

            session.setEndTime(start.plus(movieDuration));

            sessionService.create(session);
        }

        return "afisha";
    }
}
