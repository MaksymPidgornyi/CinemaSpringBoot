package com.model.entity;

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
    @OneToMany(fetch = FetchType.EAGER, mappedBy="director")
    private Set<Film> films;

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}
