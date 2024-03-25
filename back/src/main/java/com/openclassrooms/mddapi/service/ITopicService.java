package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;

public interface ITopicService {

	/**
	 * Returns a list of all topics.
	 * 
	 * @return List<TopicDto>
	 */
	List<TopicDto> getAllTopics();

}
