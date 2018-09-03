package com.apenkovsky.validators;

import com.apenkovsky.entity.Feedback;
import com.apenkovsky.entity.Ticket;
import com.apenkovsky.entity.User;
import com.apenkovsky.enums.Role;
import com.apenkovsky.enums.State;
import com.apenkovsky.service.FeedbackService;
import com.apenkovsky.service.TicketService;
import com.apenkovsky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketValidator {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private FeedbackService feedbackService;

    public boolean isValidForLeavingFeedback(Long ticketId, String username) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User user = userService.loadUserByEmail(username);
        Feedback feedback = feedbackService.getFeedback(ticketId);
        if (ticket.getState() != State.DONE || ticket.getOwner().getId() != user.getId() || feedback != null) {
            return false;
        }
        return true;
    }

    public boolean isValidForAssignment(Long ticketId, String username) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User user = userService.loadUserByEmail(username);
        if (ticket.getState() == State.APPROVED && user.getRole() == Role.ENGINER) {
            return true;
        }
        return false;
    }

    public boolean isValidForCancel(Long ticketId, String username) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User user = userService.loadUserByEmail(username);

        boolean isAbleForEmployee = (ticket.getState() == State.DECLINED || ticket.getState() == State.DECLINED) && user.getRole() == Role.EMPLOYEE;
        boolean isAbleForManager = ((ticket.getState() == State.DECLINED || ticket.getState() == State.DECLINED) &&
                user.getRole() == Role.MANAGER && ticket.getOwner().getId() == user.getId()) ||
                (ticket.getState() == State.NEW && user.getRole() == Role.MANAGER && ticket.getOwner().getId() != user.getId());
        boolean isAbleForEngineer = ticket.getState() == State.APPROVED && user.getRole() == Role.ENGINER;
        if (isAbleForEmployee || isAbleForEngineer || isAbleForManager) {
            return true;
        }
        return false;
    }

    public boolean isValidForApprovement(Long ticketId, String username) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User user = userService.loadUserByEmail(username);

        if (ticket.getState() == State.NEW && ticket.getOwner().getId() != user.getId() && user.getRole() == Role.MANAGER) {
            return true;
        }
        return false;
    }

    public boolean isValidForDecline(Long ticketId, String username) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User user = userService.loadUserByEmail(username);

        if (ticket.getState() == State.NEW && ticket.getOwner().getId() != user.getId() && user.getRole() == Role.MANAGER) {
            return true;
        }
        return false;
    }

    public boolean isValidForDone(Long ticketId, String username) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User user = userService.loadUserByEmail(username);

        if (ticket.getState() == State.IN_PROGRESS || ticket.getAssignee().getId() == user.getId() || user.getRole() == Role.ENGINER) {
            return true;
        }
        return false;
    }

    public boolean isValidForSubmit(Long ticketId, String username) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User user = userService.loadUserByEmail(username);

        if ((ticket.getState() == State.DECLINED || ticket.getState() == State.DRAFT) && ticket.getOwner().getId() == user.getId()) {
            return true;
        }
        return false;
    }

    public boolean isValidForDeletingAttachment(Long ticketId, String currentUsername) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User currentUser = userService.loadUserByEmail(currentUsername);

        if (ticket.getState() != State.DRAFT || ticket.getOwner().getId() != currentUser.getId()) {
            return false;
        }
        return true;
    }

    public boolean isValidForAddingAttachment(Long ticketId, String currentUsername) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User currentUser = userService.loadUserByEmail(currentUsername);

        if (ticket.getState() != State.DRAFT || ticket.getOwner().getId() != currentUser.getId()) {
            return false;
        }
        return true;
    }
}
