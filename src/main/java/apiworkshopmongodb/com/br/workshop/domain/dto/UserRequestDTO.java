package apiworkshopmongodb.com.br.workshop.domain.dto;

import apiworkshopmongodb.com.br.workshop.domain.User;

public record UserRequestDTO(String name, String email) {

    public UserRequestDTO(User user) {
        this(user.getName(), user.getEmail());
    }
}
