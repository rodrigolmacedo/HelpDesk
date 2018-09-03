package com.apenkovsky.controller.rest;

import com.apenkovsky.entity.Feedback;
import com.apenkovsky.service.FeedbackService;
import com.apenkovsky.validators.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
public class FeedbackRestController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private TicketValidator ticketValidator;

    @GetMapping(value = "/ticket/{ticketId}/feedback")
    public Feedback getFeedback(@PathVariable Long ticketId) {
        return feedbackService.getFeedback(ticketId);
    }

    @PostMapping(value = "/ticket/{ticketId}/feedback")
    public void leaveFeedbck(@PathVariable Long ticketId, @RequestBody Feedback feedback, Authentication authentication) {
        if (ticketValidator.isValidForLeavingFeedback(ticketId, authentication.getName())) {
            feedbackService.leaveFeedback(ticketId, feedback, authentication.getName());
        }
    }
}
