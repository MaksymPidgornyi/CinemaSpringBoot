package com.model.entity;

import com.model.entity.enums.Genre;
import lombok.*;
import org.hibernate.validator.constraints.time.DurationMax;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.Set;


@Entity
@Table(name="films")
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long filmId;
    @NotBlank
    private String filmName;
    @ElementCollection(targetClass = Genre.class, fetch = FetchType.EAGER)
    @CollectionTable(name="film_genre",
                joinColumns={@JoinColumn(name="film_id")}
    )
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<Genre> filmGenre;
    @NotNull
    @PastOrPresent
    private Year created;
    @NotNull
    @DurationMax(minutes = 120L)
    private Duration duration;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Director director;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "film")
    private Set<Session> sessions;
    @ManyToMany(mappedBy = "films", fetch = FetchType.EAGER)
    @NotNull
    @NotEmpty
    private Set<Actor> actors;

    public void setDuration(String duration){
        this.duration = Duration.ofMinutes(Long.parseLong(duration));
    }
}
