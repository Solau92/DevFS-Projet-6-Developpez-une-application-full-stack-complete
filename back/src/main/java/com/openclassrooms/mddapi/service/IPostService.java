package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRegisterDto;
import com.openclassrooms.mddapi.exception.PostNotFoundException;

public interface IPostService {

    public List<PostDto> getAll();

    public PostDto save(PostRegisterDto postRegisterDto);

    public PostDto findById(Long id) throws PostNotFoundException;

}
