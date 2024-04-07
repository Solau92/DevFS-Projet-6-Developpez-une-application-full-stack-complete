package com.openclassrooms.mddapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;

@Service
public class PostService implements IPostService {

	private PostRepository postRepository;

	private PostMapper postMapper;
 
	private static final Logger log = LoggerFactory.getLogger(PostService.class);

	
	public PostService(PostRepository postRepository, PostMapper postMapper) {
		this.postRepository = postRepository;
		this.postMapper = postMapper;
	}

	@Override
	public List<PostDto> getAll() {

		log.debug("Searching all posts");

		List<Post> posts = postRepository.findAll();
		
		return postMapper.toDto(posts);
	}
	
}
