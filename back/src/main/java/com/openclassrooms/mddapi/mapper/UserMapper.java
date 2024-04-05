package com.openclassrooms.mddapi.mapper;

import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;

import org.mapstruct.Mapper;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

    
}
