package com.openclassrooms.mddapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.response.PostsResponse;
import com.openclassrooms.mddapi.service.IPostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private IPostService postService;

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
	public ResponseEntity<PostsResponse> getAllPosts() {
		log.info("/posts : Getting the list of all posts");
		return ResponseEntity.status(HttpStatus.OK).body(new PostsResponse(postService.getAll()));
		
	}
    
}
