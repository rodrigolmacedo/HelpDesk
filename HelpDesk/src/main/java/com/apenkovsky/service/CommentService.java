package com.apenkovsky.service;

import com.apenkovsky.dto.CommentDto;
import com.apenkovsky.dto.TicketDto;
import com.apenkovsky.entity.Comment;

import java.util.List;

public interface CommentService {

    void save (CommentDto comment, Long tickedId, String email);

    List<Comment> loadTicketComments(Long ticketId);

    List<CommentDto> loadCommentsDto(Long ticketId);

    Comment buildAndSaveFromTicketDto(TicketDto ticketDto, String username, Long ticketId);
}
