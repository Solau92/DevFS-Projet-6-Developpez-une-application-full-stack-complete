package com.openclassrooms.mddapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.exception.UserNotFoundException;
import com.openclassrooms.mddapi.service.ISubscriptionService;

@RestController
@RequestMapping("/api") // TODO : voir si ok
public class SubscriptionController {

    private ISubscriptionService subscriptionService;

    private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    public SubscriptionController(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // TODO : voir si je crée une DtoResponse ou si je renvoie directement le Dto

    /**
     * TODO : à compléter 
     * @param subscriptionDto
     * @return
     * @throws UserNotFoundException 
     */
    // @PostMapping("/topic/subscription")
    // public ResponseEntity<?> save(@Valid @RequestBody SubscriptionDto subscriptionDto) {
	// 	log.info("/topic/subscription, post : Saving a new subscription");
    //     return ResponseEntity.status(HttpStatus.CREATED).body(this.subscriptionService.create(subscriptionDto));
    // }

    @PostMapping("/topic/subscription")
    public ResponseEntity<?> save(Authentication authentication, @RequestBody Long topicId) throws UserNotFoundException {
		log.info("/topic/subscription, post : Saving a new subscription on topic with id {}", topicId);
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(this.subscriptionService.create(topicId, email));
    }

    @GetMapping("/subscription/{id}")
    public ResponseEntity<?> all(@PathVariable("id") String id) throws NumberFormatException, UserNotFoundException {
        log.info("/subscription, get : Getting all the subscriptions of user with id : {}", id);

        return ResponseEntity.status(HttpStatus.OK).body(this.subscriptionService.getAll(id));
    }

    @DeleteMapping("/topic/unsubscription/{topicId}")
    public ResponseEntity<?> delete(Authentication authentication, @PathVariable("topicId") String topicId) throws UserNotFoundException {
        String email = authentication.getName();
        log.info("/topic/subscription, post : Removing a subscription on topic with id {} for user with email {}", topicId, email);
        subscriptionService.delete(Long.valueOf(topicId), email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
