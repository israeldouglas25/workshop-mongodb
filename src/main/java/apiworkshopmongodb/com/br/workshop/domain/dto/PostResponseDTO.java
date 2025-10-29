package apiworkshopmongodb.com.br.workshop.domain.dto;

import apiworkshopmongodb.com.br.workshop.domain.Post;

import java.time.LocalDate;

public record PostResponseDTO(String id, LocalDate date, String title, String body) {
    public PostResponseDTO(Post post) {
        this(post.getId(), post.getDate(), post.getTitle(), post.getBody());
    }
}
