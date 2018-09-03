package com.apenkovsky.service.implementations;

import com.apenkovsky.converters.CommentConverter;
import com.apenkovsky.dto.CommentDto;
import com.apenkovsky.dto.TicketDto;
import com.apenkovsky.entity.Comment;
import com.apenkovsky.entity.Ticket;
import com.apenkovsky.entity.User;
import com.apenkovsky.repository.CommentRepository;
import com.apenkovsky.service.CommentService;
import com.apenkovsky.service.TicketService;
import com.apenkovsky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CommentConverter commentConverter;

    @Override
    public void save(CommentDto comment, Long tickedId, String email) {
        User user = userService.loadUserByEmail(email);
        Ticket ticket = ticketService.getTicketById(tickedId);
        if (user == null || ticket == null) {
            throw new IllegalArgumentException();
        }
        comment.setUser(user);
        comment.setTicket(ticket);
        Optional<Comment> optionalComment = commentConverter.convertToEntity(comment);
        if (optionalComment.isPresent()) {
            commentRepository.save(optionalComment.get());
        }
    }

    @Override
    public List<Comment> loadTicketComments(Long ticketId) {
        return commentRepository.loadTicketComments(ticketId);
    }

    @Override
    public List<CommentDto> loadCommentsDto(Long ticketId) {
        List<Comment> comments = commentRepository.loadTicketComments(ticketId);
        List<CommentDto> commentDto = new ArrayList<>();
        for (Comment c : comments) {
            commentDto.add(commentConverter.convertToDto(c).get());
        }
        return commentDto;
    }

    @Override
    public Comment buildAndSaveFromTicketDto(TicketDto ticketDto, String username, Long ticketId) {
        Comment newComment = new Comment();
        User user = userService.loadUserByEmail(username);
        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket == null || user == null) {
            throw new IllegalArgumentException();
        }
        if (ticketDto.getComment() != null && ticketDto.getComment() != "") {
            newComment.setText(ticketDto.getComment());
            newComment.setTicket(ticket);
            newComment.setUser(user);
            CommentDto commentDto = commentConverter.convertToDto(newComment).get();
            save(commentDto, ticketId, username);
        }
        return newComment;
    }
}
