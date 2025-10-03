package apiworkshopmongodb.com.br.workshop.interfaces;

import apiworkshopmongodb.com.br.workshop.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
