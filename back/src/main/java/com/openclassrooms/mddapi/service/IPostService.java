package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostRegisterDto;

public interface IPostService {

    public List<PostDto> getAll();

    public PostDto save(PostRegisterDto postRegisterDto);

}
