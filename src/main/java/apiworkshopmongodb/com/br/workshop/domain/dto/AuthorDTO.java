package apiworkshopmongodb.com.br.workshop.domain.dto;

import apiworkshopmongodb.com.br.workshop.domain.User;

public record AuthorDTO(String id, String name) {
    public AuthorDTO(User user) {
        this(user.getId(), user.getName());
    }
}
