package com.openclassrooms.mddapi.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRegisterDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.exception.PostNotFoundException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.service.CommentsService;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	private TopicService topicService;
	private CommentsService commentsService;
	private UserService userService;
 
	private PostMapper postMapper;

	private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

	
	public PostServiceImpl(PostRepository postRepository, TopicService topicService, 
	CommentsService commentsService,
	UserService userService,
	PostMapper postMapper) {
		this.postRepository = postRepository;
		this.postMapper = postMapper;
		this.topicService = topicService;
		this.commentsService = commentsService;
		this.userService = userService;
	}

	@Override
	public List<PostDto> getAll() {

		log.debug("Searching all posts");

		List<Post> posts = postRepository.findAll();
		
		return postMapper.toDto(posts);
	}

	@Override
	public PostDto save(PostRegisterDto postRegisterDto) {

		log.debug("Trying to save post with title {}", postRegisterDto.getTitle());

		// TODO : tester si found / not --> already exists
		TopicDto topicDto = topicService.getTopicById(Long.valueOf(postRegisterDto.getTopic()));

		// TODO : créer mapping 
		PostDto postDto = new PostDto();
		postDto.setAuteur(postRegisterDto.getAuteur());
		postDto.setTitle(postRegisterDto.getTitle());
		postDto.setContent(postRegisterDto.getContent());
		postDto.setCreatedAt(LocalDate.now());
		postDto.setUpdatedAt(LocalDate.now());

		postDto.setTopic(topicDto);

		return postMapper.toDto(postRepository.save(postMapper.toEntity(postDto)));
	}

	@Override
	public PostDto findById(Long id) throws PostNotFoundException {
		
		log.debug("Searching post with id {}", id);

		Optional<Post> optionalPost = postRepository.findById(id);

		    if (!optionalPost.isPresent()) {
            log.error("Post with id {} not found", id);
            throw new PostNotFoundException("Post with" + id + "not found");
        }

		PostDto postDtoFound = postMapper.toDto(optionalPost.get());

		log.debug("Searching comments of post with id {}", id);

		List<CommentDto> commentDtos = commentsService.getAll(postMapper.toEntity(postDtoFound));
		postDtoFound.setComments(commentDtos);

		return postDtoFound;
	}

	@Override
	public PostDto addComment(Long postId, String content, String userEmail) throws UserNotFoundException, PostNotFoundException {

		log.debug("Adding comment to post with id {} by user whith email {}", postId, userEmail);

		// Récupérer l'utilisateur auteur du commentaire
        UserDto userDto = userService.findByEmail(userEmail);

		// Récupérer le post 
        PostDto postDto = this.findById(postId);

		// Ajouter le commentaire en base de données
        CommentDto newComment = commentsService.create(content, userDto, postDto);

        postDto.getComments().add(newComment);

		return postDto;
	}
	
}
