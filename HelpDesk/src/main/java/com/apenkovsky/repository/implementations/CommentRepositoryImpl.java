package com.apenkovsky.repository.implementations;

import com.apenkovsky.entity.Comment;
import com.apenkovsky.repository.CommentRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(Comment comment) {
        sessionFactory.getCurrentSession().merge(comment);
    }

    @Override
    @Transactional
    public List<Comment> loadTicketComments(Long id) {
        List<Comment> comments;
        String query = "from Comment c where ticket.id=?";
        comments = sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, id)
                .list();
        return comments;
    }
}
