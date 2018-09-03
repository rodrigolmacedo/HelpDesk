package com.apenkovsky.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "attachment")
public class Attachment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "blob", length = 1024 * 5 * 1024)
    @NotNull
    private byte[] blob;

    @JoinColumn(name = "ticket_id")
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Ticket ticket;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "type")
    @NotNull
    private String contentType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
