package com.controller;

import com.model.entity.enums.Genre;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

class ControllerUtils {
    static Map<String, String> getGenreMap(){
        Map<String, String> genre_genreName = new LinkedHashMap<>();

        genre_genreName.put(Genre.ACTION.name(), "page.addmovie.genre.action");
        genre_genreName.put(Genre.ADVENTURE.name(), "page.addmovie.genre.adventure");
        genre_genreName.put(Genre.COMEDY.name(), "page.addmovie.genre.comedy");
        genre_genreName.put(Genre.CRIME.name(), "page.addmovie.genre.crime");
        genre_genreName.put(Genre.DRAMA.name(), "page.addmovie.genre.drama");
        genre_genreName.put(Genre.HISTORICAL.name(), "page.addmovie.genre.historical");
        genre_genreName.put(Genre.HORROR.name(), "page.addmovie.genre.horror");
        genre_genreName.put(Genre.MUSICAL.name(), "page.addmovie.genre.musical");
        genre_genreName.put(Genre.SCIENCE_FICTION.name(), "page.addmovie.genre.scifi");
        genre_genreName.put(Genre.WAR.name(), "page.addmovie.genre.war");
        genre_genreName.put(Genre.WESTERN.name(), "page.addmovie.genre.western");

        return genre_genreName;
    }

    static Map<String, String> getErrorsMap(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldList -> fieldList.getField() + "Error", FieldError::getDefaultMessage));
    }
}
