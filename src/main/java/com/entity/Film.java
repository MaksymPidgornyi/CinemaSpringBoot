package com.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Year;

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
    private String filmGenre;
    private Year created;
    @ManyToOne(fetch = FetchType.EAGER)
    private Director director;
}
