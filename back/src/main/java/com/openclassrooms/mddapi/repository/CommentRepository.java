package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Searches in database all the comments related to a given post. 
     * 
     * @param post
     * @return List<Comment>
     */
    public List<Comment> findByPost(Post post);

}
