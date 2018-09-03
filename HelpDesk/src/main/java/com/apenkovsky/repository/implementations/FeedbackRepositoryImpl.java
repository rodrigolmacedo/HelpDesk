package com.apenkovsky.repository.implementations;

import com.apenkovsky.entity.Feedback;
import com.apenkovsky.repository.FeedbackRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FeedbackRepositoryImpl implements FeedbackRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Feedback loadFeedback(Long ticketId) {
        String query = "from Feedback f where f.ticket.id=?";
        Feedback feedback = (Feedback) sessionFactory.getCurrentSession().createQuery(query)
                .setParameter(0, ticketId)
                .uniqueResult();
        return feedback;
    }

    @Override
    @Transactional
    public void saveFeedback(Feedback feedback) {
        sessionFactory.getCurrentSession().save(feedback);
    }
}
