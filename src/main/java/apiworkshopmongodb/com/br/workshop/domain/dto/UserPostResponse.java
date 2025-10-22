package apiworkshopmongodb.com.br.workshop.domain.dto;

import apiworkshopmongodb.com.br.workshop.domain.User;

public record UserPostResponse(String id, String name) {
    public UserPostResponse(User user) {
        this(user.getId(), user.getName());
    }
}
