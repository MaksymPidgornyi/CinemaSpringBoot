package com.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long actorId;
    private String firstName;
    private String lastName;
    @ManyToMany
    @JoinTable(name="actor_film",
               joinColumns={@JoinColumn(name="actorId")},
               inverseJoinColumns = {@JoinColumn(name="filmId")})
    private Set<Film> films;
}
