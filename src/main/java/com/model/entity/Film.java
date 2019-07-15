package com.model.entity;

import com.model.entity.enums.Genre;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.Year;
import java.util.Set;

@Entity
@Table(name="films")
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long filmId;
    private String filmName;
    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name="film_genre",
                joinColumns={@JoinColumn(name="film_id")}
    )
    @Enumerated(EnumType.STRING)
    private Set<Genre> filmGenre;

    private Year created;
    private LocalTime duration;
    @ManyToOne(fetch = FetchType.EAGER)
    private Director director;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "film")
    private Set<Session> sessions;
    @ManyToMany(mappedBy = "films", fetch = FetchType.EAGER)
    private Set<Actor> actors;
}
