package apiworkshopmongodb.com.br.workshop.domain;

public record UserRequestDTO(String name, String email) {

    public UserRequestDTO(User user) {
        this(user.getName(), user.getEmail());
    }
}
