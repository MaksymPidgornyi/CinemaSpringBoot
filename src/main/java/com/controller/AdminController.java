package com.controller;

import com.controller.utils.ControllerUtils;
import com.model.entity.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private ActorService actorService;
    private DirectorService directorService;
    private MovieService movieService;

    @Autowired
    public AdminController(ActorService actorService, DirectorService directorService, MovieService movieService) {
        this.actorService = actorService;
        this.directorService = directorService;
        this.movieService = movieService;
    }

    @GetMapping("/add-movie")
    public String getAddFilm(Model model) {

        model.addAttribute("genres", ControllerUtils.getGenreMap());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("directors", directorService.getAllDirectors());

        return "addFilm";
    }

    @PostMapping("/add-movie")
    public String postAddMovie(@Valid Film film, BindingResult bindingResult, Model model) {

        System.out.println(film);

        model.addAttribute("genres", ControllerUtils.getGenreMap());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("directors", directorService.getAllDirectors());

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrorsMap(bindingResult);

            model.mergeAttributes(errors);
        } else {
            movieService.createMovie(film);
        }
        return "addFilm";
    }


    @GetMapping("/add-artist")
    public String getAddArtist() {
        return "addArtst";
    }

    @PostMapping("/actor")
    public String postActor(@Valid Actor actor, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors())
            actorService.addActor(actor);
        return "redirect:/add-artist";
    }


    @PostMapping("/director")
    public String postDirector(Director director) {
        directorService.addDirector(director);
        return "redirect:/add-artist";
    }

    @DeleteMapping("/del-movie/{film}")
    public void deleteMovie(@PathVariable Film film){
        movieService.deleteMovie(film);
    }
}
