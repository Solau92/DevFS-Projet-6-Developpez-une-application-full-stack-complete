package com.openclassrooms.mddapi.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	private TopicRepository topicRepository;

	private TopicMapper topicMapper;

	private static final Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);

	public TopicServiceImpl(TopicRepository topicRepository, TopicMapper topicMapper) {
		this.topicRepository = topicRepository;
		this.topicMapper = topicMapper;
	}

	/**
	 * Searches all topics in database.
	 * 
	 * @return List<TopicDto>
	 */
	@Override
	public List<TopicDto> getAll() {

		log.debug("Searching all topics");

		List<Topic> topics = topicRepository.findAll();

		return topicMapper.toDto(topics);
	}

	/**
	 * Searches in database a topic given its id.
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public TopicDto findById(Long id) {

		// TO DO : tester si found / not ? Voir pourquoi ?

		log.debug("Searching topic with id  {}", id);

		return topicMapper.toDto(topicRepository.findById(id).get());
	}

}
