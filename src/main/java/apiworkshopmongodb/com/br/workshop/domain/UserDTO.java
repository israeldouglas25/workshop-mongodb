package apiworkshopmongodb.com.br.workshop.domain;

public record UserDTO(String id, String name) {

    public UserDTO(User user) {
        this(user.getId(), user.getName());
    }
}
