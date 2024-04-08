package com.openclassrooms.mddapi.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRegisterDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.repository.PostRepository;

@Service
public class PostService implements IPostService {

	private PostRepository postRepository;

	private PostMapper postMapper;

	private ITopicService topicService;
 
	private static final Logger log = LoggerFactory.getLogger(PostService.class);

	
	public PostService(PostRepository postRepository, PostMapper postMapper
	,ITopicService topicService) {
		this.postRepository = postRepository;
		this.postMapper = postMapper;
		this.topicService = topicService;
	}

	@Override
	public List<PostDto> getAll() {

		log.debug("Searching all posts");

		List<Post> posts = postRepository.findAll();
		
		return postMapper.toDto(posts);
	}

	@Override
	public PostDto save(PostRegisterDto postRegisterDto) {

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
	
}
