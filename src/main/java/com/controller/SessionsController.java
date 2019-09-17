package com.controller;

import com.controller.utils.ControllerUtils;
import com.model.entity.*;
import com.model.entity.dto.TicketsDto;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.constant.ApplicationConstants.*;

@Controller
@RequestMapping("/afisha")
public class SessionsController {
    private SessionService sessionService;
    private MovieService movieService;
    private TicketService ticketService;
    private UserService userService;

    @Autowired
    public SessionsController(SessionService sessionService, MovieService movieService,
                              TicketService ticketService, UserService userService) {
        this.sessionService = sessionService;
        this.movieService = movieService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping
    public String getSessions(@RequestParam(name = "date", required = false) String date, Model model, Pageable pageable) {

        LocalDate sessionsDate = Objects.nonNull(date) && !date.isEmpty() ? LocalDate.parse(date) : LocalDate.now();
        Page<Session> sessionPage = sessionService.getSessionsByDate(sessionsDate, pageable);
        boolean hasGap = ControllerUtils.checkIfGapBetweenSessionsExists(sessionPage.getContent());
        Integer dailyAttendance = sessionService.getSessionsByDate(sessionsDate)
                .stream()
                .map(s -> s.getTickets().size()).reduce(0, Integer::sum);

        model.addAttribute("dailyAttendance", dailyAttendance);
        model.addAttribute("rowsNumber", ROWS);
        model.addAttribute("seatsPerRow", SEATS_PER_ROW);
        model.addAttribute("sizes", SIZES);
        model.addAttribute("hasGap", hasGap);
        model.addAttribute("sessions", sessionPage);
        model.addAttribute("movies", movieService.getAllMovies());
        model.addAttribute("sessionsDate", sessionsDate);
        model.addAttribute("minSessionStartTime", MIN_SESSION_START_TIME);
        model.addAttribute("maxSessionStartTime", MAX_SESSION_START_TIME);

        model.addAttribute("monthDates", ControllerUtils.getCurrentMonthDates(sessionsDate));

        return "afisha";
    }

    @GetMapping("/session/{session}")
    public String sessionTickets(@PathVariable Session session, Model model){
        List<Ticket> tickets = ticketService.getTicketsBySession(session);
        List<Integer> sessionPlaces = tickets.stream().map(Ticket::getPlaceNumber).collect(Collectors.toList());

        model.addAttribute("rowsNumber", ROWS);
        model.addAttribute("seatsPerRow", SEATS_PER_ROW);
        model.addAttribute("minSessionStartTime", MIN_SESSION_START_TIME);
        model.addAttribute("maxSessionStartTime", MAX_SESSION_START_TIME);
        model.addAttribute("sessionPlaces", sessionPlaces);
        model.addAttribute("session", session);


        return "tickets";
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/session/{session}/tickets")
    public String buySessionTickets(@PathVariable Session session, @Valid TicketsDto tickets,
                                    BindingResult bindingResult, Model model){

        List<Integer> places = tickets.getPlaces();
        boolean hasDuplicates = ticketService.getTicketsBySession(session)
                .stream()
                .anyMatch(p -> places.contains(p.getPlaceNumber()));

        Optional<User> user = tickets.getUser() == null ? Optional.empty() : userService.getUserByLogin(tickets.getUser());
        boolean userIsAbsent = !user.isPresent();

        if(bindingResult.hasErrors() || hasDuplicates || userIsAbsent){
            Map<String, String> errors = ControllerUtils.getErrorsMap(bindingResult);
            model.mergeAttributes(errors);
            return "redirect:/afisha/session/{session}";
        }
        else{
            places.forEach(p -> {
                Ticket ticket = new Ticket();

                ticket.setSession(tickets.getSession());
                ticket.setPlaceNumber(p);
                ticket.setUser(user.get());

                ticketService.create(ticket);
            });
        }

        return "redirect:/afisha";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/session")
    public String postSession(@Valid Session session, BindingResult bindingResult, Model model) {

        List<Session> sessions = sessionService.getSessionsByDate(session.getSessionDate());
        Film film = session.getFilm()                   == null ? new Film()            : session.getFilm();
        Duration movieDuration = film.getDuration()     == null ? Duration.ofMinutes(0) : film.getDuration();
        LocalTime start = session.getStartTime()        == null ? LocalTime.of(0, 0) : session.getStartTime();
        LocalTime end = start                           == null ? LocalTime.of(0, 0) : start.plus(movieDuration);
        session.setEndTime(start.plus(movieDuration));

        for(Session s : sessions){
            if( !(start.isBefore(s.getStartTime()) && end.isBefore(s.getStartTime()))
                    && !(start.isAfter(s.getEndTime()) && end.isAfter(s.getEndTime()))){
                bindingResult.addError(new ObjectError("timeError", "error.intervalcrossing"));
            }
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrorsMap(bindingResult);

            model.mergeAttributes(errors);
        }
        else {
            sessionService.create(session);
        }

        return "redirect:/afisha";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteSession(@PathVariable Session id){
        sessionService.deleteSession(id);
        return "redirect:/afisha?date=" + id.getSessionDate();
    }

}
