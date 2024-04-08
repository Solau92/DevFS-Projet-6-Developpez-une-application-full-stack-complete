package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.model.Post;

public interface ICommentsService {
    
    public List<CommentDto> getAll(Post post);
    
}
