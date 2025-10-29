package apiworkshopmongodb.com.br.workshop.domain.dto;

import apiworkshopmongodb.com.br.workshop.domain.Comment;

import java.time.LocalDate;

public record CommentDTO(
        String text,
        LocalDate date,
        AuthorDTO author) {

    public CommentDTO(Comment comment) {
        this(comment.getText(), comment.getDate(), comment.getAuthor());
    }

}
