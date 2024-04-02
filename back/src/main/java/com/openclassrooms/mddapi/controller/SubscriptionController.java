package com.openclassrooms.mddapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.service.ISubscriptionService;

@RestController
@RequestMapping("/api/topic") // TODO : voir si ok
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
     */
    @PostMapping("/subscription")
    public ResponseEntity<?> create(@Valid @RequestBody SubscriptionDto subscriptionDto) {
		log.info("/topic/subscription, post : Saving a new subscription");
        return ResponseEntity.status(HttpStatus.CREATED).body(this.subscriptionService.create(subscriptionDto));
    }

}
