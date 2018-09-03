package com.apenkovsky.repository.implementations;

import com.apenkovsky.entity.HistoryItem;
import com.apenkovsky.repository.HistoryRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<HistoryItem> loadHistoryByTicketId(Long ticketId) {
        String query = "from HistoryItem item where item.ticket.id=?";
        List<HistoryItem> history = sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, ticketId)
                .list();
        return history;
    }

    @Override
    @Transactional
    public void saveItem(HistoryItem historyItem) {
        sessionFactory.getCurrentSession().save(historyItem);
    }
}
