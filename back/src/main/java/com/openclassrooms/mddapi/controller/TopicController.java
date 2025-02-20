package com.openclassrooms.mddapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.response.TopicsResponse;
import com.openclassrooms.mddapi.service.TopicService;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
	
	private TopicService topicService;

	private static final Logger log = LoggerFactory.getLogger(TopicController.class);
	
	public TopicController(TopicService topicService) {
		this.topicService = topicService;		
	}

	/**
	 * Getting all the topics.
	 * 
	 * @return ResponseEntity<TopicsResponse> with status ok, and containing all the topics
	 */
	@GetMapping("")
	public ResponseEntity<TopicsResponse> getAll() {

		log.info("(get) /topic : Getting the list of all topics");
		
		return ResponseEntity.status(HttpStatus.OK).body(new TopicsResponse(topicService.getAll()));
	}
		
}
