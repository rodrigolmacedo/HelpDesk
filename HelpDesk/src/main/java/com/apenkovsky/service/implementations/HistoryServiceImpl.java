package com.apenkovsky.service.implementations;

import com.apenkovsky.converters.HistoryConverter;
import com.apenkovsky.dto.HistoryItemDto;
import com.apenkovsky.entity.HistoryItem;
import com.apenkovsky.entity.Ticket;
import com.apenkovsky.entity.User;
import com.apenkovsky.repository.HistoryRepository;
import com.apenkovsky.service.HistoryService;
import com.apenkovsky.service.TicketService;
import com.apenkovsky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryConverter historyConverter;

    @Override
    public List<HistoryItem> loadHistoryByTicketId(Long ticketId) {
        return historyRepository.loadHistoryByTicketId(ticketId);
    }

    @Override
    public void saveItem(HistoryItem historyItem) {
        historyRepository.saveItem(historyItem);
    }

    @Override
    public void buildItemForCreation(Ticket ticket) {
        HistoryItem historyItem = new HistoryItem();
        historyItem.setTicket(ticket);
        historyItem.setUser(ticket.getOwner());
        historyItem.setAction("Ticket was created");
        historyItem.setDescription("Ticket was created");
        saveItem(historyItem);
    }

    @Override
    public void buildItemForEdition(Ticket ticket) {
        HistoryItem newItem = new HistoryItem();
        newItem.setTicket(ticket);
        newItem.setUser(ticket.getOwner());
        newItem.setAction("Ticket was edited");
        newItem.setDescription("Ticket was edited");
        saveItem(newItem);
    }

    @Override
    public void buildItemForChangingStatus(Ticket beforeTicket, String username, Long ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User user = userService.loadUserByEmail(username);
        HistoryItem historyItem = new HistoryItem();
        historyItem.setTicket(ticket);
        historyItem.setUser(user);
        historyItem.setAction("Ticket status is changed");
        historyItem.setDescription("Ticket status is changed from '" + beforeTicket.getState() + "' to '" + ticket.getState() + "'");
        saveItem(historyItem);
    }

    @Override
    public List<HistoryItemDto> getTicketHistoryDto(Long ticketId) {
        List<HistoryItem> history = loadHistoryByTicketId(ticketId);
        List<HistoryItemDto> historyDto = new ArrayList<>();
        for (HistoryItem item : history) {
            Optional<HistoryItemDto> historyItemDtoOptional = historyConverter.convertToDto
                    (item, userService.loadUserById(item.getUser().getId()));
            if (historyItemDtoOptional.isPresent()) {
                historyDto.add(historyItemDtoOptional.get());
            }
        }
        return historyDto;
    }
}
