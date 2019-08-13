package com.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="tickets")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticketId;
    private int placeNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    private Session session;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
