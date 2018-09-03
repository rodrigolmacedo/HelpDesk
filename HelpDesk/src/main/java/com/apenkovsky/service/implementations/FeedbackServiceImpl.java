package com.apenkovsky.service.implementations;

import com.apenkovsky.entity.Feedback;
import com.apenkovsky.entity.Ticket;
import com.apenkovsky.entity.User;
import com.apenkovsky.repository.FeedbackRepository;
import com.apenkovsky.service.FeedbackService;
import com.apenkovsky.service.TicketService;
import com.apenkovsky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    @Override
    public Feedback getFeedback(Long ticketId) {
        return feedbackRepository.loadFeedback(ticketId);
    }

    @Override
    public void leaveFeedback(Long ticketId, Feedback feedback, String username) {
        User user = userService.loadUserByEmail(username);
        Ticket ticket = ticketService.getTicketById(ticketId);
        feedback.setTicket(ticket);
        feedback.setUser(user);
        feedbackRepository.saveFeedback(feedback);
    }
}
