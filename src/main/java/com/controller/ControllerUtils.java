package com.controller;

import com.model.entity.enums.Genre;

import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerUtils {
    static Map<Genre, String> getGenreList(){
        Map<Genre, String> genre_genreName = new LinkedHashMap<>();

        genre_genreName.put(Genre.ACTION, "page.addfilm.genre.action");
        genre_genreName.put(Genre.ADVENTURE, "page.addfilm.genre.adventure");
        genre_genreName.put(Genre.COMEDY, "page.addfilm.genre.comedy");
        genre_genreName.put(Genre.CRIME, "page.addfilm.genre.crime");
        genre_genreName.put(Genre.DRAMA, "page.addfilm.genre.drama");
        genre_genreName.put(Genre.HISTORICAL, "page.addfilm.genre.historical");
        genre_genreName.put(Genre.HORROR, "page.addfilm.genre.horror");
        genre_genreName.put(Genre.MUSICAL, "page.addfilm.genre.musical");
        genre_genreName.put(Genre.SCIENCE_FICTION, "page.addfilm.genre.scifi");
        genre_genreName.put(Genre.WAR, "page.addfilm.genre.war");
        genre_genreName.put(Genre.WESTERN, "page.addfilm.genre.western");

        return genre_genreName;
    }
}
