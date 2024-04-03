package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.UserRegisterDto;
import com.openclassrooms.mddapi.model.User;

@Component
@Mapper(componentModel = "spring")
public interface UserRegisterMapper extends EntityMapper<UserRegisterDto, User>{
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Override
    public User toEntity(UserRegisterDto dto);
}
