package com.openclassrooms.mddapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.CommentRegisterDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRegisterDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.response.PostsResponse;
import com.openclassrooms.mddapi.exception.PostNotFoundException;
import com.openclassrooms.mddapi.exception.TopicNotFoundException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    // TODO : voir si je laisse une PostResponse ?
    
    /**
     * Gets all the posts. 
     * 
     * @return ResponseEntity<PostsResponse> with status ok, and containing all the posts
     */
    @GetMapping("")
	public ResponseEntity<PostsResponse> getAll() {

		log.info("(get) /posts : Getting the list of all posts");

		return ResponseEntity.status(HttpStatus.OK).body(new PostsResponse(postService.findAll()));
	}

    /**
     * Creates a new post.
     * 
     * @param postRegisterDto
     * @param authentication
     * @return ResponseEntity<PostDto> with status created, and containing the saved post
     * @throws UserNotFoundException
     * @throws TopicNotFoundException 
     * @throws NumberFormatException 
     */
    @PostMapping("")
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostRegisterDto postRegisterDto, Authentication authentication) throws UserNotFoundException, NumberFormatException, TopicNotFoundException {
        
        log.info("(post) /posts : Trying to register post with title {}", postRegisterDto.getTitle());

        String email = authentication.getName();
        UserDto userDto = userService.findByEmail(email);
        
        postRegisterDto.setAuteur(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postRegisterDto));
    }

    /**
     * Gets a post, given its id.
     * 
     * @param id
     * @return ResponseEntity<PostDto> with status ok, and containing the post saved
     * @throws PostNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long id) throws PostNotFoundException {

        log.info("(get) /posts/{} : Searching post with id {}", id, id);

        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
    }

    /**
     * Creates a new comment related to the post which id is given and the logged user.
     *  
     * @param postId
     * @param commentRegisterDto
     * @param authentication
     * @return ResponseEntity<PostDto> with status created, and containing the comment saved
     * @throws UserNotFoundException
     * @throws PostNotFoundException
     */
    @PostMapping("/{postId}/comment")
    public ResponseEntity<PostDto> createComment(@PathVariable Long postId, @RequestBody CommentRegisterDto commentRegisterDto, Authentication authentication) throws UserNotFoundException, PostNotFoundException {

        log.info("(post) /posts/{}/comment : Trying to register comment to article with id {}", postId, postId);

        String email = authentication.getName();
        PostDto updatedPost = postService.addComment(postId, commentRegisterDto.getContent(), email);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPost);
    }

}
