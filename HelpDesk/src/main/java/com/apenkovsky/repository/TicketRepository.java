package com.apenkovsky.repository;

import com.apenkovsky.dto.TicketDto;
import com.apenkovsky.entity.Ticket;

import java.util.List;

public interface TicketRepository {

    Ticket loadById(Long id);

    List<Ticket> loadTicketsForEmployee(Long id);

    List<Ticket> loadTicketsForManager(Long id);

    List<Ticket> loadTicketsForEngineer(Long id);

    void save(Ticket ticket);

    Ticket update(Ticket ticket);

    TicketDto getTicketDto(Long ticketId);

    Ticket loadTicketById(Long id);

    void assign(Long ticketId, String username);

    void approve(Long ticketId, String username);

    void cancel(Long ticketId, String username);

    void decline(Long ticketId, String username);

    void done(Long ticketId, String username);

    void submit(Long ticketId, String username);
}
