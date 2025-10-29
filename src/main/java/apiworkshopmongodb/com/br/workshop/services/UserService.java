package apiworkshopmongodb.com.br.workshop.services;

import apiworkshopmongodb.com.br.workshop.domain.Post;
import apiworkshopmongodb.com.br.workshop.domain.User;
import apiworkshopmongodb.com.br.workshop.domain.dto.AuthorDTO;
import apiworkshopmongodb.com.br.workshop.domain.dto.UserRequestDTO;
import apiworkshopmongodb.com.br.workshop.domain.dto.UserResponseDTO;
import apiworkshopmongodb.com.br.workshop.exceptions.NotFoundException;
import apiworkshopmongodb.com.br.workshop.interfaces.PostRepository;
import apiworkshopmongodb.com.br.workshop.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDTO::new).toList();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User save(UserRequestDTO userRequestDTO) {
        User user = new User(null, userRequestDTO.name(), userRequestDTO.email());
        return userRepository.save(user);
    }

    public void savePost(User user) {
        userRepository.save(user);
    }

    public void delete(String id) {
        User existingUser = findById(id);
        postRepository.deleteByAuthorId(existingUser.getId());
        userRepository.deleteById(existingUser.getId());
    }

    public User update(String id, UserRequestDTO userRequestDTO) {
        User existingUser = findById(id);
        updateData(existingUser, userRequestDTO);
        User savedUser = userRepository.save(existingUser);

        List<Post> posts = postRepository.findByAuthorId(savedUser.getId());
        if (posts != null && !posts.isEmpty()) {
            for (Post post : posts) {
                if (post.getAuthor() != null) {
                    post.setAuthor(new AuthorDTO(savedUser));
                }
            }
            postRepository.saveAll(posts);
        }
        return savedUser;
    }

    private void updateData(User existingUser, UserRequestDTO userRequestDTO) {
        if (userRequestDTO.name() != null && !userRequestDTO.name().isEmpty()) {
            existingUser.setName(userRequestDTO.name());
        }
        if (userRequestDTO.email() != null && !userRequestDTO.email().isEmpty()) {
            existingUser.setEmail(userRequestDTO.email());
        }
    }

}
