package apiworkshopmongodb.com.br.workshop.domain.dto;

import apiworkshopmongodb.com.br.workshop.domain.User;

public record UserResponseDTO(String name, String email) {

    public UserResponseDTO(User user) {
        this(user.getName(), user.getEmail());
    }
}
