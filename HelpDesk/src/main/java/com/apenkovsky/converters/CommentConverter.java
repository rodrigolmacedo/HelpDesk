package com.apenkovsky.converters;

import com.apenkovsky.dto.CommentDto;
import com.apenkovsky.entity.Comment;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;

@Component
public class CommentConverter {

    public Optional<CommentDto> convertToDto(Comment comment) {
        Optional<CommentDto> commentDto = Optional.empty();
        if (comment != null) {
            commentDto = Optional.of(CommentDto.CommentDtoBuilder.aCommentDto()
                    .withId(comment.getId())
                    .withTicket(comment.getTicket())
                    .withDate(comment.getDate())
                    .withText(comment.getText())
                    .withUser(comment.getUser())
                    .build());
        }
        return commentDto;
    }

    public Optional<Comment> convertToEntity(CommentDto commentDto) {
        Optional<Comment> result = Optional.empty();
        if (commentDto != null && commentDto.getTicket() != null && commentDto.getText() != "" && commentDto.getUser() != null) {
            Comment comment = new Comment();
            comment.setId(commentDto.getId());
            comment.setTicket(commentDto.getTicket());
            comment.setUser(commentDto.getUser());
            comment.setText(commentDto.getText());
            Timestamp date = new Timestamp(new java.util.Date().getTime());
            comment.setDate(date);
            result = Optional.of(comment);
        }
        return result;
    }
}
