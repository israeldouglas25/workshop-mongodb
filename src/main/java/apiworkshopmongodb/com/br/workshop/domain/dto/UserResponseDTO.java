package apiworkshopmongodb.com.br.workshop.domain.dto;

import apiworkshopmongodb.com.br.workshop.domain.Post;
import apiworkshopmongodb.com.br.workshop.domain.User;

import java.util.List;

public record UserResponseDTO(String name, String email, List<Post> posts) {

    public UserResponseDTO(User user) {
        this(user.getName(), user.getEmail(), user.getPosts());
    }
}
