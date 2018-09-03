package com.apenkovsky.service;

import com.apenkovsky.dto.TicketDto;
import com.apenkovsky.entity.Ticket;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TicketService {

    List<Ticket> loadTicketsForEmployee(Long id);

    List<Ticket> loadTicketsForManager(Long id);

    List<Ticket> loadTicketsForEngineer(Long id);

    Ticket getTicketById(Long id);

    Ticket update(Ticket ticket);

    Ticket updateFromDto(TicketDto ticketDto, String currentUsername);

    void saveNewTicket(String ticketDtoJson, MultipartFile[] attachments, String username);

    void save(Ticket ticket);

    TicketDto getTicketDto(Long ticketId);

    void assign(Long ticketId, String username);

    void approve(Long ticketId, String username);

    void cancel(Long ticketId, String username);

    void decline(Long ticketId, String username);

    void done(Long ticketId, String username);

    void submit(Long ticketId, String username);
}
