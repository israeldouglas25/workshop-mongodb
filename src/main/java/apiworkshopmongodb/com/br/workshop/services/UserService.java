package apiworkshopmongodb.com.br.workshop.services;

import apiworkshopmongodb.com.br.workshop.domain.User;
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
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User save(User user) {
        return userRepository.save(new User(null, user.getName(), user.getEmail()));
    }

    public void delete(String id) {
        User existingUser = findById(id);
        if (existingUser.getId().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty or null");
        }
        userRepository.deleteById(existingUser.getId());
    }

    public User update(User user) {
        User existingUser = findById(user.getId());
        if (existingUser.getId() != null && existingUser.getId().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty or null");
        }
        updateData(existingUser, user);
        return userRepository.save(existingUser);
    }

    private void updateData(User existingUser, User user) {
        if (user.getName() != null && !user.getName().isEmpty()) {
            existingUser.setName(user.getName());
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            existingUser.setEmail(user.getEmail());
        }
    }

}
