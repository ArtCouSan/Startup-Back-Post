package br.com.posts.endpoint.controller;

import br.com.posts.endpoint.dto.PostAlterDTO;
import br.com.posts.endpoint.dto.PostListDTO;
import br.com.posts.endpoint.dto.PostSaveDTO;
import br.com.posts.endpoint.entity.Post;
import br.com.posts.endpoint.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/post")
public class PostsController {

    private final PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody PostSaveDTO postSaveDTO) {
        return new ResponseEntity<Post>(this.postService.savePost(postSaveDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> alterPost(@PathVariable Long id, @RequestBody PostAlterDTO postAlterDTO){
        return new ResponseEntity<Post>(this.postService.alterPost(id, postAlterDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id){
        return new ResponseEntity<Post>(this.postService.deletePost(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findPost(@PathVariable Long id){
        return new ResponseEntity<Post>(this.postService.findPost(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> listPost() {
        return new ResponseEntity<List<Post>>(this.postService.listPost(), HttpStatus.OK);
    }

}
