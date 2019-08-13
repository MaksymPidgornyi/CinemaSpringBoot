package com.service;

import com.model.entity.Session;
import com.model.entity.Ticket;

import java.util.List;

public interface TicketService {
    void create(Ticket ticket);
    List<Ticket> getTicketsBySession(Session session);
}
