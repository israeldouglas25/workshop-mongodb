package apiworkshopmongodb.com.br.workshop.domain;

public record UserResponseDTO(String name, String email) {

    public UserResponseDTO(User user) {
        this(user.getName(), user.getEmail());
    }
}
