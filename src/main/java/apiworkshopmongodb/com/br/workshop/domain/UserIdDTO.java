package apiworkshopmongodb.com.br.workshop.domain;

public record UserIdDTO(String id, String name, String email) {

    public UserIdDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
