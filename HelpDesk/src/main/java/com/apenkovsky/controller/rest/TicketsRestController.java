package com.apenkovsky.controller.rest;

import com.apenkovsky.dto.TicketDto;
import com.apenkovsky.service.TicketService;
import com.apenkovsky.validators.TicketValidator;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/ticket")
public class TicketsRestController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketValidator ticketValidator;


    @PostMapping(value = "/")
    public void createTicket(@RequestParam(required = false) MultipartFile[] files,
                             Authentication authentication,
                             @RequestParam(value = "ticketDto") String ticketDtoJson) throws InvalidDataException {
        ticketService.saveNewTicket(ticketDtoJson, files, authentication.getName());
    }

    @GetMapping(value = "/{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TicketDto getTicket(@PathVariable Long ticketId) throws InvalidDataException {
        return ticketService.getTicketDto(ticketId);
    }

    @PutMapping(value = "/{ticketId}")
    public void editTicket(@PathVariable Long ticketId, @RequestBody TicketDto ticketDto, Authentication authentication) {
        ticketService.updateFromDto(ticketDto, authentication.getName());
    }

    @PutMapping(value = "/{ticketId}/assign")
    public void assignTicket(@PathVariable Long ticketId, Authentication authentication) throws ValidationException {
        if (ticketValidator.isValidForAssignment(ticketId, authentication.getName())) {
            ticketService.assign(ticketId, authentication.getName());
        } else {
            throw new ValidationException("Invalid parameters");
        }
    }

    @PutMapping(value = "/{ticketId}/approve")
    public void approveTicket(@PathVariable Long ticketId, Authentication authentication) throws ValidationException {
        if (ticketValidator.isValidForApprovement(ticketId, authentication.getName())) {
            ticketService.approve(ticketId, authentication.getName());
        } else {
            throw new ValidationException("Invalid parameters");
        }
    }

    @PutMapping(value = "/{ticketId}/cancel")
    public void cancelTicket(@PathVariable Long ticketId, Authentication authentication) throws ValidationException {
        if (ticketValidator.isValidForCancel(ticketId, authentication.getName())) {
            ticketService.cancel(ticketId, authentication.getName());
        } else {
            throw new ValidationException("Invalid parameters");
        }
    }

    @PutMapping(value = "/{ticketId}/submit")
    public void submitTicket(@PathVariable Long ticketId, Authentication authentication) throws ValidationException {
        if (ticketValidator.isValidForSubmit(ticketId, authentication.getName())) {
            ticketService.submit(ticketId, authentication.getName());
        } else {
            throw new ValidationException("Invalid parameters");
        }
    }

    @PutMapping(value = "/{ticketId}/decline")
    public void declineTicket(@PathVariable Long ticketId, Authentication authentication) throws ValidationException {
        if (ticketValidator.isValidForDecline(ticketId, authentication.getName())) {
            ticketService.decline(ticketId, authentication.getName());
        } else {
            throw new ValidationException("Invalid parameters");
        }
    }

    @PutMapping(value = "/{ticketId}/done")
    public void doneTicket(@PathVariable Long ticketId, Authentication authentication) throws ValidationException {
        if (ticketValidator.isValidForDone(ticketId, authentication.getName())) {
            ticketService.done(ticketId, authentication.getName());
        } else {
            throw new ValidationException("Invalid parameters");
        }
    }

}
