package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;

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
	 * @return
	 */
	public TopicDto findById(Long id);

}
