package com.openclassrooms.mddapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.response.TopicsResponse;
import com.openclassrooms.mddapi.service.ITopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {
	
	private ITopicService topicService;

	private static final Logger log = LoggerFactory.getLogger(TopicController.class);
	
	public TopicController(ITopicService topicService) {
		this.topicService = topicService;		
	}

	/**
	 * Getting all topics. 
	 * 
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<TopicsResponse> getAllTopics() {
		log.info("/topic : Getting the list of all topics");
		return ResponseEntity.status(HttpStatus.OK).body(new TopicsResponse(topicService.getAllTopics()));
		
	}
	
	
}
