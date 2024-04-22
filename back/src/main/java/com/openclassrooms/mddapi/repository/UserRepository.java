package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Searches a user in database, given an email.
     * 
     * @param post
     * @return List<Comment>
     */
    Optional<User> findByEmail(String email);

}
