package apiworkshopmongodb.com.br.workshop.services;

import apiworkshopmongodb.com.br.workshop.domain.Comment;
import apiworkshopmongodb.com.br.workshop.domain.Post;
import apiworkshopmongodb.com.br.workshop.domain.User;
import apiworkshopmongodb.com.br.workshop.domain.dto.AuthorDTO;
import apiworkshopmongodb.com.br.workshop.domain.dto.CommentDTO;
import apiworkshopmongodb.com.br.workshop.interfaces.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment create(String idPost, CommentDTO dto) {
        Post post = postService.findById(idPost);
        User user = userService.findById(dto.getAuthor());
        AuthorDTO author = new AuthorDTO(user);
        Comment comment = new Comment(null, dto.getText(), LocalDate.now(), author);
        Comment saved = commentRepository.save(comment);
        post.getComments().add(saved);
        postService.saveComment(post);
        return comment;
    }
}
