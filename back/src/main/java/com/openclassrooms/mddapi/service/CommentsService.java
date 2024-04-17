package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Post;

public interface CommentsService {
    
    public List<CommentDto> getAll(Post post);

    public CommentDto save(String content, UserDto auteurd, PostDto post);
    
}
