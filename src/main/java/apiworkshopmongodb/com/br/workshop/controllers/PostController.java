package apiworkshopmongodb.com.br.workshop.controllers;

import apiworkshopmongodb.com.br.workshop.domain.Post;
import apiworkshopmongodb.com.br.workshop.domain.dto.PostDTO;
import apiworkshopmongodb.com.br.workshop.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostDTO dto) {
        Post postd = postService.create(dto);
        URI uri = UriComponentsBuilder.fromPath("/posts/{id}").buildAndExpand(postd.getId()).toUri();
        return ResponseEntity.created(uri).body(postd);
    }
}
