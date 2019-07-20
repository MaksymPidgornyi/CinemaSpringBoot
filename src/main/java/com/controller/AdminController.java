package com.controller;

import com.model.entity.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

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
    public String getAddFilm(Model model){

        model.addAttribute("genres", ControllerUtils.getGenreMap());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("directors", directorService.getAllDirectors());

        return "addFilm";
    }

    @PostMapping("/add-movie")
    public String postAddMovie(@Valid Film film, BindingResult bindingResult, Model model){
        System.out.println(film);

        model.addAttribute("genres", ControllerUtils.getGenreMap());
        model.addAttribute("actors", actorService.getAllActors());
        model.addAttribute("directors", directorService.getAllDirectors());

        if(bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrorsMap(bindingResult);

            model.mergeAttributes(errors);
        }
        else{
            movieService.createMovie(film);
        }
        return "addFilm";
    }


    @GetMapping("/add-artist")
    public String getAddArtist(){
        return "addArtst";
    }
    @PostMapping("/actor")
    public String postActor(Actor actor){
        actorService.addActor(actor);
        return "redirect:/add-artist";
    }


    @PostMapping("/director")
    public String postDirector(Director director){
        directorService.addDirector(director);
        return "redirect:/add-artist";
    }
}
