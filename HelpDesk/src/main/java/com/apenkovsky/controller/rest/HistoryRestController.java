package com.apenkovsky.controller.rest;

import com.apenkovsky.dto.HistoryItemDto;
import com.apenkovsky.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HistoryRestController {

    @Autowired
    private HistoryService historyService;

    @GetMapping(value = "/ticket/{ticketId}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HistoryItemDto> getTicketHistory(@PathVariable Long ticketId) {
        return historyService.getTicketHistoryDto(ticketId);
    }
}
