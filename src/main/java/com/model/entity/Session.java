package com.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name="sessions")
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sessionId;
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Film film;
    @NotNull
    private LocalTime startTime;
    private LocalTime endTime;
    @NotNull
    private LocalDate sessionDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "session")
    private Set<Ticket> tickets;
}
