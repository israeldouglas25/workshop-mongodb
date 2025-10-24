package apiworkshopmongodb.com.br.workshop.services;

import apiworkshopmongodb.com.br.workshop.domain.Post;
import apiworkshopmongodb.com.br.workshop.domain.User;
import apiworkshopmongodb.com.br.workshop.domain.dto.PostDTO;
import apiworkshopmongodb.com.br.workshop.domain.dto.UserPostResponse;
import apiworkshopmongodb.com.br.workshop.interfaces.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post create(PostDTO dto) {
        User user = userService.findById(dto.getIdAuthor());
        UserPostResponse author = new UserPostResponse(user);
        Post post = new Post(null, LocalDate.now(), dto.getTitle(), dto.getBody(), author);
        Post postSave = postRepository.save(post);
        user.getPosts().add(postSave);
        userService.savePost(user);
        return postSave;
    }
}
