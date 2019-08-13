package com.service;

import com.model.entity.Session;
import com.model.entity.Ticket;
import com.model.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void create(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getTicketsBySession(Session session) {
        return ticketRepository.findTicketsBySession(session);
    }
}
