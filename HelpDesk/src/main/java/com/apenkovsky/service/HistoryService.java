package com.apenkovsky.service;

import com.apenkovsky.dto.HistoryItemDto;
import com.apenkovsky.entity.HistoryItem;
import com.apenkovsky.entity.Ticket;


import java.util.List;

public interface HistoryService {

    List<HistoryItem> loadHistoryByTicketId(Long ticketId);

    void saveItem(HistoryItem historyItem);

    void buildItemForCreation(Ticket ticket);

    void buildItemForEdition(Ticket ticket);

    void buildItemForChangingStatus(Ticket beforeTicket, String username, Long ticketId);

    List<HistoryItemDto> getTicketHistoryDto(Long ticketId);
}
