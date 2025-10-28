package apiworkshopmongodb.com.br.workshop.interfaces;

import apiworkshopmongodb.com.br.workshop.domain.Post;
import apiworkshopmongodb.com.br.workshop.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    void deleteByAuthorId(String authorId);
    List<Post> findByAuthorId(String authorId);
}
