package com.openclassrooms.mddapi.mapper;

import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

    @Mapping(target = "subscriptions", ignore = true)
    @Override
    public UserDto toDto(User user);
   
}
