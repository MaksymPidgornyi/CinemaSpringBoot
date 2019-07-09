package com.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="sessions")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long sessionId;
    @ManyToOne(fetch = FetchType.EAGER)
    private Film film;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate sessionDate;
}
