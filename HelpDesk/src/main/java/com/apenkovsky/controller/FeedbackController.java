package com.apenkovsky.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

@Controller
public class FeedbackController {

    @GetMapping("/tickets/{ticketId}/leavefeedback")
    public String getLeaveFeedbackPage(@PathVariable Long ticketId, Authentication authentication, HttpServletResponse response) {
        return "leave-feedback";
    }
}
