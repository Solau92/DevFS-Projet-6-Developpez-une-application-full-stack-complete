package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRegisterDto;
import com.openclassrooms.mddapi.exception.PostNotFoundException;
import com.openclassrooms.mddapi.exception.UserNotFoundException;

public interface PostService {

    public List<PostDto> findAll();

    public PostDto save(PostRegisterDto postRegisterDto);

    public PostDto findById(Long id) throws PostNotFoundException;

    public PostDto addComment(Long postId, String content, String userEmail) throws UserNotFoundException, PostNotFoundException;

}
