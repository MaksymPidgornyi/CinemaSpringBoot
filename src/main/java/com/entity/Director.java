package com.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="directors")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long directorId;
    private String firstName;
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="filmId")
    private Set<Film> films;
}
