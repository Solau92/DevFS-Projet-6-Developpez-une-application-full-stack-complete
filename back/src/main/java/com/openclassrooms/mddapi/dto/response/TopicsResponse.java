package com.openclassrooms.mddapi.dto.response;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;

public class TopicsResponse {
    
    private List<TopicDto> topics;

    public TopicsResponse(List<TopicDto> topics) {
        this.topics = topics;
    }

    public List<TopicDto> getTopics() {
        return this.topics;
    }

    public void setTopics(List<TopicDto> topics) {
        this.topics = topics;
    }
    
}
