package com.apenkovsky.dto;


import com.apenkovsky.entity.Ticket;

import java.sql.Timestamp;

public class HistoryItemDto {
    private Long id;
    private Ticket ticket;
    private Timestamp date;
    private String action;
    private String userFirstName;
    private String userLastName;
    private String description;


    public HistoryItemDto() {
    }

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

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static final class HistoryItemDtoBuilder {
        private Long id;
        private Ticket ticket;
        private Timestamp date;
        private String action;
        private String userFirstName;
        private String userLastName;
        private String description;

        private HistoryItemDtoBuilder() {
        }

        public static HistoryItemDtoBuilder aHistoryItemDto() {
            return new HistoryItemDtoBuilder();
        }

        public HistoryItemDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public HistoryItemDtoBuilder withTicket(Ticket ticket) {
            this.ticket = ticket;
            return this;
        }

        public HistoryItemDtoBuilder withDate(Timestamp date) {
            this.date = date;
            return this;
        }

        public HistoryItemDtoBuilder withAction(String action) {
            this.action = action;
            return this;
        }

        public HistoryItemDtoBuilder withUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
            return this;
        }

        public HistoryItemDtoBuilder withUserLastName(String userLastName) {
            this.userLastName = userLastName;
            return this;
        }

        public HistoryItemDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public HistoryItemDto build() {
            HistoryItemDto historyItemDto = new HistoryItemDto();
            historyItemDto.setId(id);
            historyItemDto.setTicket(ticket);
            historyItemDto.setDate(date);
            historyItemDto.setAction(action);
            historyItemDto.setUserFirstName(userFirstName);
            historyItemDto.setUserLastName(userLastName);
            historyItemDto.setDescription(description);
            return historyItemDto;
        }
    }
}

