package apiworkshopmongodb.com.br.workshop.controllers;

import apiworkshopmongodb.com.br.workshop.domain.User;
import apiworkshopmongodb.com.br.workshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }
}
