package com.apenkovsky.dto;

import com.apenkovsky.entity.Category;
import com.apenkovsky.entity.Feedback;
import com.apenkovsky.entity.User;
import com.apenkovsky.enums.State;
import com.apenkovsky.enums.Urgency;

import java.sql.Date;

public class TicketDto {

    private Long id;
    private String name;
    private String description;
    private Date createdOn;
    private Date desiredResolutionDate;
    private User assignee;
    private User owner;
    private State state;
    private Category category;
    private Urgency urgency;
    private User approver;
    private String comment;
    private Feedback feedback;

    public TicketDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setDesiredResolutionDate(Date desiredResolutionDate) {
        this.desiredResolutionDate = desiredResolutionDate;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }


    public static final class TicketDtoBuilder {
        private Long id;
        private String name;
        private String description;
        private Date createdOn;
        private Date desiredResolutionDate;
        private User assignee;
        private User owner;
        private State state;
        private Category category;
        private Urgency urgency;
        private User approver;
        private String comment;
        private Feedback feedback;

        private TicketDtoBuilder() {
        }

        public static TicketDtoBuilder aTicketDto() {
            return new TicketDtoBuilder();
        }

        public TicketDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public TicketDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TicketDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public TicketDtoBuilder withCreatedOn(Date createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public TicketDtoBuilder withDesiredResolutionDate(Date desiredResolutionDate) {
            this.desiredResolutionDate = desiredResolutionDate;
            return this;
        }

        public TicketDtoBuilder withAssignee(User assignee) {
            this.assignee = assignee;
            return this;
        }

        public TicketDtoBuilder withOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public TicketDtoBuilder withState(State state) {
            this.state = state;
            return this;
        }

        public TicketDtoBuilder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public TicketDtoBuilder withUrgency(Urgency urgency) {
            this.urgency = urgency;
            return this;
        }

        public TicketDtoBuilder withApprover(User approver) {
            this.approver = approver;
            return this;
        }

        public TicketDtoBuilder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public TicketDtoBuilder withFeedback(Feedback feedback) {
            this.feedback = feedback;
            return this;
        }

        public TicketDto build() {
            TicketDto ticketDto = new TicketDto();
            ticketDto.setId(id);
            ticketDto.setName(name);
            ticketDto.setDescription(description);
            ticketDto.setCreatedOn(createdOn);
            ticketDto.setDesiredResolutionDate(desiredResolutionDate);
            ticketDto.setAssignee(assignee);
            ticketDto.setOwner(owner);
            ticketDto.setState(state);
            ticketDto.setCategory(category);
            ticketDto.setUrgency(urgency);
            ticketDto.setApprover(approver);
            ticketDto.setComment(comment);
            ticketDto.setFeedback(feedback);
            return ticketDto;
        }
    }
}
