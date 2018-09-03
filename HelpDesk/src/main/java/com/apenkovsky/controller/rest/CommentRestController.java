package com.apenkovsky.controller.rest;

import com.apenkovsky.dto.CommentDto;
import com.apenkovsky.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/ticket/{ticketId}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDto> loadComments(@PathVariable Long ticketId) {
        return commentService.loadCommentsDto(ticketId);
    }

    @PostMapping(value = "/ticket/{ticketId}/comments")
    public void addComment(@PathVariable Long ticketId, @RequestBody CommentDto comment, Authentication authentication) {

        commentService.save(comment, ticketId, authentication.getName());
    }
}
