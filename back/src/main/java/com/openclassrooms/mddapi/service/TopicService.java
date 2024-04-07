package com.openclassrooms.mddapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
public class TopicService implements ITopicService {

	private TopicRepository topicRepository;

	private TopicMapper topicMapper;

	private static final Logger log = LoggerFactory.getLogger(TopicService.class);

	
	public TopicService(TopicRepository topicRepository, TopicMapper topicMapper) {
		this.topicRepository = topicRepository;
		this.topicMapper = topicMapper;
	}

	/**
	 * Returns a list of all topics from database.
	 * 
	 * @return List<TopicDto>
	 */
	@Override
	public List<TopicDto> getAllTopics() {

		log.debug("Searching all topics");

		List<Topic> topics = topicRepository.findAll();
		
		return topicMapper.toDto(topics);
	}
	
}
