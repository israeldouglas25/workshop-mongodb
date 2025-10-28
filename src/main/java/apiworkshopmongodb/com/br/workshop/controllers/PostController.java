package apiworkshopmongodb.com.br.workshop.controllers;

import apiworkshopmongodb.com.br.workshop.domain.Post;
import apiworkshopmongodb.com.br.workshop.domain.dto.PostDTO;
import apiworkshopmongodb.com.br.workshop.domain.dto.PostUpdateDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostDTO dto) {
        Post postd = postService.create(dto);
        URI uri = UriComponentsBuilder.fromPath("/posts/{id}").buildAndExpand(postd.getId()).toUri();
        return ResponseEntity.created(uri).body(postd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody PostUpdateDTO dto) {
        Post updatedPost = postService.update(id, dto);
        return ResponseEntity.ok(updatedPost);
    }

}
