package com.anjesh.gethired.controller;

import com.anjesh.gethired.repository.PostRepository;
import com.anjesh.gethired.model.Post;
import com.anjesh.gethired.repository.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepo srepo;

    @GetMapping("/allPosts")
    public List<Post> getAllPosts() {
        return repo.findAll();
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text){
        return srepo.findByText(text);
    }

    @PostMapping("/addjob")
    public Post addJob(@RequestBody Post post) {
        repo.save(post);
        return post;
    }
}
