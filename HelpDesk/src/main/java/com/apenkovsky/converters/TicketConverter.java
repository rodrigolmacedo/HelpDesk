package com.apenkovsky.converters;

import com.apenkovsky.dto.TicketDto;
import com.apenkovsky.entity.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

@Component
public class TicketConverter {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Logger logger;

    public Optional<TicketDto> convertToDto(Ticket ticket) {
        Optional<TicketDto> maybeResult = Optional.empty();
        if (ticket != null) {
            maybeResult = Optional.of(TicketDto.TicketDtoBuilder
                    .aTicketDto()
                    .withId(ticket.getId())
                    .withName(ticket.getName())
                    .withDescription(ticket.getDescription())
                    .withCreatedOn(ticket.getCreatedOn())
                    .withDesiredResolutionDate(ticket.getDesiredResolutionDate())
                    .withAssignee(ticket.getAssignee())
                    .withOwner(ticket.getOwner())
                    .withState(ticket.getState())
                    .withCategory(ticket.getCategory())
                    .withUrgency(ticket.getUrgency())
                    .withApprover(ticket.getApprover())
                    .build());
        }
        return maybeResult;
    }

    public Optional<Ticket> convertToEntity(TicketDto ticketDto) {
        Optional<Ticket> maybeResult = Optional.empty();
        if (ticketDto != null) {
            Ticket ticket = new Ticket();
            if (ticketDto.getId() == null) {
                ticket.setId(0L);
            } else {
                ticket.setId(ticketDto.getId());
            }
            ticket.setName(ticketDto.getName());
            ticket.setCategory(ticketDto.getCategory());
            ticket.setDescription(ticketDto.getDescription());
            ticket.setDesiredResolutionDate(ticketDto.getDesiredResolutionDate());
            if (ticket.getDesiredResolutionDate() == null) {
                ticket.setDesiredResolutionDate(new Date(new java.util.Date().getTime()));
            }
            ticket.setAssignee(ticketDto.getAssignee());
            ticket.setApprover(ticketDto.getApprover());
            ticket.setOwner(ticketDto.getOwner());
            ticket.setUrgency(ticketDto.getUrgency());
            ticket.setState(ticketDto.getState());
            maybeResult = Optional.of(ticket);
        }
        return maybeResult;
    }

    public Optional<TicketDto> convertFromJson(String json) {
        Optional<TicketDto> maybeResult = Optional.empty();
        if (json != null && json != "") {
            try {
                maybeResult = Optional.of(objectMapper.readValue(json, TicketDto.class));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return maybeResult;
    }
}
