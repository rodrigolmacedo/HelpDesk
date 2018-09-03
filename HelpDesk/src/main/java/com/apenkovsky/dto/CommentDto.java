package com.apenkovsky.dto;

import com.apenkovsky.entity.Ticket;
import com.apenkovsky.entity.User;

import java.sql.Timestamp;


public class CommentDto {

    private Long id;
    private User user;
    private String text;
    private Timestamp date;
    private Ticket ticket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


    public static final class CommentDtoBuilder {
        private Long id;
        private User user;
        private String text;
        private Timestamp date;
        private Ticket ticket;

        private CommentDtoBuilder() {
        }

        public static CommentDtoBuilder aCommentDto() {
            return new CommentDtoBuilder();
        }

        public CommentDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CommentDtoBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public CommentDtoBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public CommentDtoBuilder withDate(Timestamp date) {
            this.date = date;
            return this;
        }

        public CommentDtoBuilder withTicket(Ticket ticket) {
            this.ticket = ticket;
            return this;
        }

        public CommentDto build() {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(id);
            commentDto.setUser(user);
            commentDto.setText(text);
            commentDto.setDate(date);
            commentDto.setTicket(ticket);
            return commentDto;
        }
    }
}
