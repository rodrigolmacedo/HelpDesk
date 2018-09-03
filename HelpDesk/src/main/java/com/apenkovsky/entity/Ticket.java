package com.apenkovsky.entity;

import com.apenkovsky.enums.State;
import com.apenkovsky.enums.Urgency;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;


    @Size(max = 100)
    @Column(name = "name")
    private String name;


    @Size(max = 100)
    @Column(name = "description")
    private String description;


    @Column(name = "created_on")
    private Date createdOn= new Date(new java.util.Date().getTime());


    @Column(name = "desired_resolution_date")
    private Date desiredResolutionDate = new Date(new java.util.Date().getTime());

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @NotNull
    @JoinColumn(name = "owner_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;

    @Column(name = "state_id")
    @Enumerated(EnumType.ORDINAL)
    private State state;

    @NotNull
    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @NotNull
    @Column(name = "urgency_id")
    @Enumerated(EnumType.ORDINAL)
    private Urgency urgency;

    @JoinColumn(name = "approver_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User approver;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(Date desiresResolutionDate) {
        this.desiredResolutionDate = desiresResolutionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }
}
