package com.openclassrooms.mddapi.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
public class TopicService implements ITopicService {

	private TopicRepository topicRepository;
	
	public TopicService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

	/**
	 * Returns a list of all topics from database.
	 * 
	 * @return List<TopicDto>
	 */
	@Override
	public List<TopicDto> getAllTopics() {

		List<Topic> topics = topicRepository.findAll();
		
		List<TopicDto> topicsDto = new ArrayList<>();

		ModelMapper mapper = new ModelMapper();
		for (Topic t : topics) {
			TopicDto tDto = mapper.map(t, TopicDto.class);
			topicsDto.add(tDto);
		}

		return topicsDto;
	}
	
}
