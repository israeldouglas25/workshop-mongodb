package apiworkshopmongodb.com.br.workshop.controllers;

import apiworkshopmongodb.com.br.workshop.domain.User;
import apiworkshopmongodb.com.br.workshop.domain.UserDTO;
import apiworkshopmongodb.com.br.workshop.domain.UserIdDTO;
import apiworkshopmongodb.com.br.workshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTO = users.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserIdDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserIdDTO(user));
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
