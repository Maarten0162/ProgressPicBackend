package com.Maarten0162.ProgressPicBackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Maarten0162.ProgressPicBackend.model.Post;
import com.Maarten0162.ProgressPicBackend.model.PostDTO;
import com.Maarten0162.ProgressPicBackend.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService service;

    public PostController(PostService Service){
        this.service = Service;
    }
    @PostMapping("/create")
    public Post createPost( @RequestBody PostDTO post ) throws Exception{
            return service.createPost(post);
    }
}
