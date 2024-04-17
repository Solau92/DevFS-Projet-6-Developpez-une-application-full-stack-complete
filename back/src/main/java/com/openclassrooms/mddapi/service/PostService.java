package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRegisterDto;
import com.openclassrooms.mddapi.exception.PostNotFoundException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;

public interface PostService {

    /**
     * Searches all the posts.
     * 
     * @return List<PostDto>
     */
    public List<PostDto> findAll();

    /**
     * Saves a new post. 
     * 
     * @param postRegisterDto
     * @return PostDto, the post saved
     */
    public PostDto save(PostRegisterDto postRegisterDto);

    /**
     * Searches a post, given its id.
     * 
     * @param id
     * @return PostDto, the post found
     * @throws PostNotFoundException
     */
    public PostDto findById(Long id) throws PostNotFoundException;

    /**
     * Saves a comment given its content, the user email and the post id.
     * 
     * @param postId
     * @param content
     * @param userEmail
     * @return PostDto, the post with the new comment
     * @throws UserNotFoundException
     * @throws PostNotFoundException
     */
    public PostDto addComment(Long postId, String content, String userEmail) throws UserNotFoundException, PostNotFoundException;

}
