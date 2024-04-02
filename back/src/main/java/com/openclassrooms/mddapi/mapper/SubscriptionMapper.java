package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.model.Subscription;

@Component
@Mapper(componentModel = "spring")
public interface SubscriptionMapper extends EntityMapper<SubscriptionDto, Subscription> {

    
} 
