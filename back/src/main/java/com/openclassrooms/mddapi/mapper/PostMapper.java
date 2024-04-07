package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.model.Post;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostDto, Post> {
    
}
