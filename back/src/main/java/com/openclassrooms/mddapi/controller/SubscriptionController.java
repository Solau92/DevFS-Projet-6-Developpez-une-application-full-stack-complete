package com.openclassrooms.mddapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.exception.SubscriptionAlreadyExistsException;
import com.openclassrooms.mddapi.exception.SubscriptionNotFoundException;
import com.openclassrooms.mddapi.exception.TopicNotFoundException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.service.SubscriptionService;

@RestController
@RequestMapping("/api/topic")
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * Creates a new subscription for the logged user, to to the topic which id is given. 
     * 
     * @param authentication
     * @param topicId
     * @return ResponseEntity<SubscriptionDto>, with status created, and containing the new subscription
     * @throws UserNotFoundException
     * @throws SubscriptionAlreadyExistsException
     * @throws TopicNotFoundException 
     */
    @PostMapping("/subscription")
    public ResponseEntity<SubscriptionDto> save(Authentication authentication, @RequestBody Long topicId) throws UserNotFoundException, SubscriptionAlreadyExistsException, TopicNotFoundException {
		
        String email = authentication.getName();

        log.info("(post) /topic/subscription : Saving a new subscription on topic with id {} for user with email {}", topicId, email);    
        
        return ResponseEntity.status(HttpStatus.CREATED).body(this.subscriptionService.save(topicId, email));
    }

    /**
     * Deletes the subscription for the logged user, to to the topic which id is given. 
     * 
     * @param authentication
     * @param topicId
     * @return ResponseEntity<Void> with status ok 
     * @throws UserNotFoundException
     * @throws NumberFormatException
     * @throws SubscriptionNotFoundException
     */
    @DeleteMapping("/unsubscription/{topicId}")
    public ResponseEntity<Void> delete(Authentication authentication, @PathVariable("topicId") String topicId) throws UserNotFoundException, NumberFormatException, SubscriptionNotFoundException {
        
        String email = authentication.getName();
        
        log.info("(post) /topic/subscription : Removing a subscription on topic with id {} for user with email {}", topicId, email);
        
        subscriptionService.delete(Long.valueOf(topicId), email);
        
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
