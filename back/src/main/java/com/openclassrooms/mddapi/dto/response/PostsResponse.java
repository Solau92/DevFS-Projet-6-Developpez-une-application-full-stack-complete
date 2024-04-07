package com.openclassrooms.mddapi.dto.response;

import java.util.List;

import com.openclassrooms.mddapi.dto.PostDto;

public class PostsResponse {

    private List<PostDto> posts;

    public PostsResponse(List<PostDto> posts) {
        this.posts = posts;
    }

    public List<PostDto> getPosts() {
        return this.posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }
    
}
