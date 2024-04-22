package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.model.Post;

public interface CommentsService {
    
    /**
     * Searches all the comments related to a given post.
     * 
     * @param post
     * @return List<CommentDto>
     */
    public List<CommentDto> getAll(Post post);

    /**
     * Saves a comment, given tis content, the user, and the related post.
     * 
     * @param content
     * @param auteur
     * @param post
     * @return CommentDto
     */
    public CommentDto save(String content, UserDto auteur, PostDto post);
    
}
