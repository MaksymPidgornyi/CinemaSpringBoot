package com.model.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.servlet.LocaleResolver;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
@Table(name="actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long actorId;
    @NotBlank(message = "message.error.first.name")
    @Length(max = 30, message = "message.error.toolong")
    private String firstName;
    @NotBlank(message = "message.error.last.name")
    @Length(max = 30, message = "message.error.toolong")
    private String lastName;
    @ManyToMany
    @JoinTable(name="actor_film",
               joinColumns={@JoinColumn(name="actorId")},
               inverseJoinColumns = {@JoinColumn(name="filmId")})
    private Set<Film> films;
}
