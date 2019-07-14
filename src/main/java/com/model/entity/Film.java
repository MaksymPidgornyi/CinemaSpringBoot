package com.model.entity;

import com.model.entity.enums.Genre;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.Year;
import java.util.Set;

@Entity
@Table(name="films")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long filmId;
    private String filmName;
    @Enumerated(EnumType.STRING)
    private Genre filmGenre;
    private Year created;
    private Duration duration;
    @ManyToOne(fetch = FetchType.EAGER)
    private Director director;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "film")
    private Set<Session> sessions;
}
