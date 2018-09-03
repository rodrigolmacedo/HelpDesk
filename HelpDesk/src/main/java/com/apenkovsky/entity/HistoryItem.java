package com.apenkovsky.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "history")
public class HistoryItem {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "ticket_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Ticket ticket;

    @Column(name = "date")
    @NotNull
    private Timestamp date = new Timestamp(new Date().getTime());

    @Column(name = "action")
    @NotNull
    private String action;

    @JoinColumn(name = "user_id")
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User user;

    @Column(name = "description")
    @NotNull
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
