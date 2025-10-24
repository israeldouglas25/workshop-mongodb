package apiworkshopmongodb.com.br.workshop.services;

import apiworkshopmongodb.com.br.workshop.domain.User;
import apiworkshopmongodb.com.br.workshop.domain.dto.UserRequestDTO;
import apiworkshopmongodb.com.br.workshop.exceptions.NotFoundException;
import apiworkshopmongodb.com.br.workshop.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
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
        userRepository.deleteById(existingUser.getId());
    }

    public User update(String id, UserRequestDTO userRequestDTO) {
        User existingUser = findById(id);
        updateData(existingUser, userRequestDTO);
        return userRepository.save(existingUser);
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
