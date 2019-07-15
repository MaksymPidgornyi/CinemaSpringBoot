package com.model.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "message.error.first.name")
    @Length(max = 30, message = "message.error.toolong")
    private String firstName;
    @NotBlank(message = "message.error.first.name")
    @Length(max = 30, message = "message.error.toolong")
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="director")
    private Set<Film> films;

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}
