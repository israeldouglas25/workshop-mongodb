package apiworkshopmongodb.com.br.workshop.controllers;

import apiworkshopmongodb.com.br.workshop.domain.Comment;
import apiworkshopmongodb.com.br.workshop.domain.dto.CommentDTO;
import apiworkshopmongodb.com.br.workshop.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestParam String idPost, @RequestBody CommentDTO dto) {
        Comment createdComment = commentService.create(idPost, dto);
        URI uri = UriComponentsBuilder.fromPath("/comments/{id}").buildAndExpand(createdComment.getId()).toUri();
        return ResponseEntity.created(uri).body(createdComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
