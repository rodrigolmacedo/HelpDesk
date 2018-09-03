package com.apenkovsky.repository;

import com.apenkovsky.entity.Comment;

import java.util.List;

public interface CommentRepository {

    void save(Comment comment);

    List<Comment> loadTicketComments(Long ticketId);
}
