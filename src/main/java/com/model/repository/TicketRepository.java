package com.model.repository;

import com.model.entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Long, Ticket> {
}
