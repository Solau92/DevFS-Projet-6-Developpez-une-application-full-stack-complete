package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.exception.TopicNotFoundException;

public interface TopicService {

	/**
	 * Searches all topics.
	 * 
	 * @return List<TopicDto>
	 */
	public List<TopicDto> getAll();

	/**
	 * Searches a topic given its id.
	 * 
	 * @param id
	 * @return TopicDto, the topic found
	 * @throws TopicNotFoundException 
	 */
	public TopicDto findById(Long id) throws TopicNotFoundException;

}
