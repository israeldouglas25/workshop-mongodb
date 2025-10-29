package apiworkshopmongodb.com.br.workshop.services;

import apiworkshopmongodb.com.br.workshop.domain.Post;
import apiworkshopmongodb.com.br.workshop.domain.User;
import apiworkshopmongodb.com.br.workshop.domain.dto.AuthorDTO;
import apiworkshopmongodb.com.br.workshop.domain.dto.PostDTO;
import apiworkshopmongodb.com.br.workshop.domain.dto.PostUpdateDTO;
import apiworkshopmongodb.com.br.workshop.exceptions.NotFoundException;
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

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
    }

    public Post create(PostDTO dto) {
        User user = userService.findById(dto.getIdAuthor());
        AuthorDTO author = new AuthorDTO(user);
        Post post = new Post(null, LocalDate.now(), dto.getTitle(), dto.getBody(), author);
        Post postSave = postRepository.save(post);
        user.getPosts().add(postSave);
        userService.savePost(user);
        return postSave;
    }

    public void delete(String id) {
        Post existingPost = findById(id);
        postRepository.deleteById(existingPost.getId());
    }

    public Post update(String id, PostUpdateDTO dto) {
        Post existingPost = findById(id);
        updateData(existingPost, dto);
        return postRepository.save(existingPost);
    }

    private void updateData(Post existingPost, PostUpdateDTO dto) {
        if (dto.getTitle() != null && !dto.getTitle().isEmpty()) {
            existingPost.setDate(LocalDate.now());
            existingPost.setTitle(dto.getTitle());
        }
        if (dto.getBody() != null && !dto.getBody().isEmpty()) {
            existingPost.setDate(LocalDate.now());
            existingPost.setBody(dto.getBody());
        }
    }
}
