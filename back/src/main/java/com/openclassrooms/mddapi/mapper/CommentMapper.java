package com.openclassrooms.mddapi.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.model.Comment;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {
    
}
