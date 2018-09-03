package com.apenkovsky.repository.implementations;

import com.apenkovsky.entity.Attachment;
import com.apenkovsky.repository.AttachmentRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AttachmentRepositoryImpl implements AttachmentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Attachment> loadAttachmetsByTicketId(Long ticketId) {
        String hql = "From Attachment where ticket.id=?";
        List<Attachment> attachments = (List<Attachment>) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter(0, ticketId).list();
        return attachments;
    }

    @Override
    @Transactional
    public Attachment loadAttachmentById(Long id) {
        String hql = "From Attachment where id=?";
        Attachment attachment = (Attachment) sessionFactory.getCurrentSession()
                .createQuery(hql)
                .setParameter(0, id).uniqueResult();
        return attachment;
    }

    @Override
    @Transactional
    public void save(Attachment attachment) {
        sessionFactory.getCurrentSession().save(attachment);
    }

    @Override
    @Transactional
    public void delete(Long attachmentId) {
        String hql = "delete from Attachment where id=?";
        sessionFactory.getCurrentSession().createQuery(hql)
                .setParameter(0, attachmentId)
                .executeUpdate();
    }
}
