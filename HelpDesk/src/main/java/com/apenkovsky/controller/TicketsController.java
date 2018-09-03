package com.apenkovsky.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class TicketsController {

    @Autowired
    private Logger logger;

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    public String getAllTickets() {
        return "all-tickets";
    }

    @RequestMapping(value = "/newticket", method = RequestMethod.GET)
    public String getCreationPage() {
        return "create-ticket";
    }

    @RequestMapping(value = "/overview/{ticketId}", method = RequestMethod.GET)
    public String getTicketPage(@PathVariable int ticketId) {
        return "ticket-overview";
    }

    @RequestMapping(value = "/ticket/{ticketId}/edit", method = RequestMethod.GET)
    public String getEditionPage(@PathVariable int ticketId, HttpServletResponse response, Authentication authentication) {
        return "edit-ticket";
    }

}
