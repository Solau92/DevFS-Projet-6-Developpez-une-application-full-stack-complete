package com.openclassrooms.mddapi.service.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.exception.TopicNotFoundException;
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
	 * @return TopicDto, the topic found 
	 * @throws TopicNotFoundException 
	 */
	@Override
	public TopicDto findById(Long id) throws TopicNotFoundException {

		log.debug("Searching topic with id  {}", id);

		Optional<Topic> optionalTopic = topicRepository.findById(id);

		if (!optionalTopic.isPresent()) {
			log.error("Topic with id {} not found", id);
			throw new TopicNotFoundException("Topic with" + id + "not found");
		}

		return topicMapper.toDto(optionalTopic.get());
	}

}
