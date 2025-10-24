package apiworkshopmongodb.com.br.workshop.domain.dto;

import apiworkshopmongodb.com.br.workshop.domain.User;

public record UserPostResponseDTO(String id, String name) {
    public UserPostResponseDTO(User user) {
        this(user.getId(), user.getName());
    }
}
