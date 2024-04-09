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

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.CommentRegisterDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRegisterDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.response.PostsResponse;
import com.openclassrooms.mddapi.exception.PostNotFoundException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.service.ICommentsService;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.service.IUserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private IPostService postService;

    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    private IUserService userService;

    private ICommentsService commentsService;

    public PostController(IPostService postService,
    IUserService userService,
    ICommentsService commentsService) {
        this.postService = postService;
        this.userService = userService;
        this.commentsService = commentsService;
    }

    @GetMapping("")
	public ResponseEntity<PostsResponse> getAllPosts() {
		log.info("/posts : Getting the list of all posts");
		return ResponseEntity.status(HttpStatus.OK).body(new PostsResponse(postService.getAll()));
		
	}

    @PostMapping("")
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostRegisterDto postRegisterDto, Authentication authentication) throws UserNotFoundException {
        log.info("/posts : Trying to register article with title {}", postRegisterDto.getTitle());

        // TODO : pour le User : revoir si Authentication ok ou dans front ?... 
        // Ou dans service...

        log.info("Post dans controller :" + postRegisterDto.toString());

        String email = authentication.getName();
        UserDto userDto = userService.findByEmail(email);
        
        postRegisterDto.setAuteur(userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(postRegisterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> get(@PathVariable Long id) throws PostNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<PostDto> createComment(@PathVariable Long postId, @RequestBody CommentRegisterDto commentRegisterDto, Authentication authentication) throws UserNotFoundException, PostNotFoundException {

        log.info("/posts/{id}/comment : Trying to register comment to article with id {}", postId);

        // TODO : trop de logique métier, à basculer dans service... 
        // créer méthode create commment dans post service, qui appellera comment service ??

        // Récupérer l'utilisateur auteur du commentaire
        String email = authentication.getName();
        UserDto userDto = userService.findByEmail(email);

        // Récupérer le post 
        PostDto postDto = postService.findById(postId);
        
        // Ajouter le commentaire en base de données
        CommentDto newComment = commentsService.create(commentRegisterDto.getContent(), userDto, postDto);

        // Puis recharger le post (un peu lourd...)
        // PostDto updatedPost = postService.findById(postId);
        // Ou ajouter juste le commentaire au post ? 
        postDto.getComments().add(newComment);

        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }

    
}
