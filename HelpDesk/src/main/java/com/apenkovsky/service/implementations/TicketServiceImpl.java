package com.apenkovsky.service.implementations;

import com.apenkovsky.converters.TicketConverter;
import com.apenkovsky.dto.TicketDto;
import com.apenkovsky.email.EmailSenderContext;
import com.apenkovsky.entity.Ticket;
import com.apenkovsky.entity.User;
import com.apenkovsky.enums.EmailTemplate;
import com.apenkovsky.enums.State;
import com.apenkovsky.repository.TicketRepository;
import com.apenkovsky.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private CommentService commentService;

    @Autowired
    EmailSenderContext emailContext;


    public List<Ticket> loadTicketsForEmployee(Long id) {
        return ticketRepository.loadTicketsForEmployee(id);
    }

    public List<Ticket> loadTicketsForManager(Long id) {
        return ticketRepository.loadTicketsForManager(id);
    }

    public List<Ticket> loadTicketsForEngineer(Long id) {
        return ticketRepository.loadTicketsForEngineer(id);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.loadById(id);
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
        historyService.buildItemForCreation(ticket);
        if (ticket.getState() == State.NEW) {
            emailContext.sendEmail(EmailTemplate.NEW_TICKET_FOR_APPROVAL, userService.loadManagers(), ticket.getId());
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        Ticket savedTicket = ticketRepository.update(ticket);
        historyService.buildItemForEdition(ticket);
        return savedTicket;
    }

    @Override
    public Ticket updateFromDto(TicketDto ticketDto, String currentUsername) {
        User currentUser = userService.loadUserByEmail(currentUsername);
        ticketDto.setOwner(currentUser);
        Optional<Ticket> ticketOptional = ticketConverter.convertToEntity(ticketDto);
        Ticket ticket = ticketOptional.get();
        update(ticket);
        return ticket;

    }

    @Override
    public TicketDto getTicketDto(Long ticketId) {
        return ticketRepository.getTicketDto(ticketId);
    }

    @Override
    public void assign(Long ticketId, String username) {
        Ticket ticket = getTicketById(ticketId);
        ticketRepository.assign(ticketId, username);
        historyService.buildItemForChangingStatus(ticket, username, ticketId);
    }

    @Override
    public void approve(Long ticketId, String username) {
        Ticket ticket = getTicketById(ticketId);
        ticketRepository.approve(ticketId, username);
        historyService.buildItemForChangingStatus(ticket, username, ticketId);

        List<User> recipients = userService.loadEngineers();
        recipients.add(userService.loadUserById(getTicketById(ticketId).getOwner().getId()));
        emailContext.sendEmail(EmailTemplate.TICKET_WAS_APPROVED, recipients, ticketId);

    }

    @Override
    public void cancel(Long ticketId, String username) {
        Ticket ticket = getTicketById(ticketId);
        ticketRepository.cancel(ticketId, username);
        historyService.buildItemForChangingStatus(ticket, username, ticketId);

        List<User> recipients = new ArrayList<>();
        recipients.add(userService.loadUserById(getTicketById(ticketId).getOwner().getId()));

        if (getTicketById(ticketId).getOwner().getId() != userService.loadUserByEmail(username).getId()) {
            if (getTicketById(ticketId).getState() == State.NEW) {
                emailContext.sendEmail(EmailTemplate.TICKET_WAS_CANCELLED_BY_MANAGER, recipients, ticketId);
            } else {
                recipients.add(userService.loadUserById(getTicketById(ticketId).getApprover().getId()));
                emailContext.sendEmail(EmailTemplate.TICKET_WAS_CANCELLED_BY_ENGINEER,
                        recipients, ticketId);
            }
        }
    }

    @Override
    public void decline(Long ticketId, String username) {
        Ticket ticket = getTicketById(ticketId);
        ticketRepository.decline(ticketId, username);
        historyService.buildItemForChangingStatus(ticket, username, ticketId);

        List<User> recipients = new ArrayList();
        recipients.add(userService.loadUserById(getTicketById(ticketId).getOwner().getId()));


        emailContext.sendEmail(EmailTemplate.TICKET_WAS_DECLINED, recipients, getTicketById(ticketId).getId());
    }

    @Override
    public void done(Long ticketId, String username) {
        Ticket ticket = getTicketById(ticketId);
        ticketRepository.done(ticketId, username);
        historyService.buildItemForChangingStatus(ticket, username, ticketId);

        List<User> recipients = new ArrayList();
        recipients.add(userService.loadUserById(getTicketById(ticketId).getOwner().getId()));

        emailContext.sendEmail(EmailTemplate.TICKET_WAS_DONE, recipients, ticketId);
    }

    @Override
    public void submit(Long ticketId, String username) {
        Ticket ticket = getTicketById(ticketId);
        ticketRepository.submit(ticketId, username);
        historyService.buildItemForChangingStatus(ticket, username, ticketId);

        emailContext.sendEmail(EmailTemplate.NEW_TICKET_FOR_APPROVAL, userService.loadManagers(), ticketId);
    }

    public void saveNewTicket(String ticketDtoJson, MultipartFile[] attachments, String email) {
        System.out.println(ticketDtoJson);
        Optional<TicketDto> ticketDtoOptional = ticketConverter.convertFromJson(ticketDtoJson);
        if (ticketDtoOptional.isPresent()) {
            User user = userService.loadUserByEmail(email);
            TicketDto ticketDto = ticketDtoOptional.get();
            ticketDto.setOwner(user);
            ticketDto.setCategory(categoryService.loadCategoryById(ticketDto.getCategory().getId()));
            Optional<Ticket> ticketOptional = ticketConverter.convertToEntity(ticketDto);
            Ticket newTicket;
            newTicket = ticketOptional.get();
            save(newTicket);
            if (ticketDto.getComment() != null && ticketDto.getComment() != "") {
                commentService.buildAndSaveFromTicketDto(ticketDto, user.getEmail(), newTicket.getId());
            }
            attachmentService.saveAttachments(attachments, newTicket.getId(), email);
        }
    }
}
