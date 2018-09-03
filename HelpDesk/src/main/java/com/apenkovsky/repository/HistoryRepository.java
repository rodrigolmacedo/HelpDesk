package com.apenkovsky.repository;

import com.apenkovsky.entity.HistoryItem;

import java.util.List;

public interface HistoryRepository {

    List<HistoryItem> loadHistoryByTicketId(Long ticketId);

    void saveItem(HistoryItem historyItem);
}
