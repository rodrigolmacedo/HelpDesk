package com.apenkovsky.repository.implementations;

import com.apenkovsky.converters.TicketConverter;
import com.apenkovsky.dto.TicketDto;
import com.apenkovsky.entity.User;
import com.apenkovsky.enums.State;
import com.apenkovsky.entity.Ticket;
import com.apenkovsky.repository.TicketRepository;
import com.apenkovsky.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Ticket loadById(Long id) {
        Ticket ticket;
        String query = "from Ticket t where t.id=?";
        ticket = (Ticket) sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, id)
                .uniqueResult();
        return ticket;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<Ticket> loadTicketsForEmployee(Long id) {
        List<Ticket> tickets;
        String query = "from Ticket t where t.owner.id=?";
        tickets = sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, id)
                .list();
        return tickets;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<Ticket> loadTicketsForManager(Long id) {
        List<Ticket> tickets;
        String query = "from Ticket t where t.owner.id=? or t.state=? or (t.approver.id=? and t.state not in (?,?))";
        tickets = sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, id)
                .setParameter(1, State.NEW)
                .setParameter(2, id)
                .setParameter(3, State.NEW)
                .setParameter(4, State.DRAFT)
                .list();
        return tickets;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<Ticket> loadTicketsForEngineer(Long id) {
        List<Ticket> tickets;
        String query = "from Ticket t where t.state=? or (t.assignee.id=? and t.state in (?,?)) ";
        tickets = sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, State.APPROVED)
                .setParameter(1, id)
                .setParameter(2, State.IN_PROGRESS)
                .setParameter(3, State.DONE)
                .list();
        return tickets;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(Ticket ticket) {
        sessionFactory.getCurrentSession().save(ticket);
    }

    @Override
    @Transactional
    public Ticket update(Ticket ticket) {
        String query = "from Ticket t where t.id=?";
        Ticket existingTicket = (Ticket) sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, ticket.getId())
                .uniqueResult();
        if (existingTicket == null) {
            throw new IllegalArgumentException();
        } else {
            Date date = existingTicket.getCreatedOn();
            existingTicket = ticket;
            existingTicket.setCreatedOn(date);
        }
        sessionFactory.getCurrentSession().merge(existingTicket);
        return existingTicket;
    }

    @Override
    @Transactional
    public Ticket loadTicketById(Long id) {
        String query = "from Ticket t where t.id=?";
        Ticket ticket = (Ticket) sessionFactory.getCurrentSession()
                .createQuery(query)
                .setParameter(0, id)
                .uniqueResult();
        return ticket;
    }

    @Override
    @Transactional
    public void assign(Long ticketId, String username) {
        User user = userService.loadUserByEmail(username);
        String query = "update Ticket t set t.state = ? , t.assignee = ? where t.id =?";
        sessionFactory.getCurrentSession().createQuery(query)
                .setParameter(0, State.IN_PROGRESS)
                .setParameter(1, user)
                .setParameter(2, ticketId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void approve(Long ticketId, String username) {
        User user = userService.loadUserByEmail(username);
        String query = "update Ticket t set t.state = ? , t.approver = ? where t.id = ?";
        sessionFactory.getCurrentSession().createQuery(query)
                .setParameter(0, State.APPROVED)
                .setParameter(1, user)
                .setParameter(2, ticketId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void cancel(Long ticketId, String username) {
        String query = "update Ticket t set t.state = ? where t.id = ?";
        sessionFactory.getCurrentSession().createQuery(query)
                .setParameter(0, State.CANCELED)
                .setParameter(1, ticketId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void decline(Long ticketId, String username) {
        String query = "update Ticket t set t.state = ? where t.id = ?";
        sessionFactory.getCurrentSession().createQuery(query)
                .setParameter(0, State.DECLINED)
                .setParameter(1, ticketId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void done(Long ticketId, String username) {
        String query = "update Ticket t set t.state = ? where t.id = ?";
        sessionFactory.getCurrentSession().createQuery(query)
                .setParameter(0, State.DONE)
                .setParameter(1, ticketId)
                .executeUpdate();

    }

    @Override
    @Transactional
    public void submit(Long ticketId, String username) {
        String query = "update Ticket t set t.state = ? where t.id = ?";
        sessionFactory.getCurrentSession().createQuery(query)
                .setParameter(0, State.NEW)
                .setParameter(1, ticketId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public TicketDto getTicketDto(Long ticketId) {
        Optional<TicketDto> ticketDtoOptional = ticketConverter.convertToDto(loadTicketById(ticketId));
        //ticketDtoOptional.orElseThrow(() -> new InvalidDataException("Invalid data!"));
        TicketDto ticketDto = ticketDtoOptional.get();
        //ticketDto.setFeedback(feedbackService.loadFeedbackByTicketId(ticketId));
        return ticketDto;
    }
}
