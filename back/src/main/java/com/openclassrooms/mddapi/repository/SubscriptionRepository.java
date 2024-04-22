package com.openclassrooms.mddapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.User;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Searches in database all the suscription by a given user. 
     * 
     * @param user
     * @return List<Subscription>
     */
    public List<Subscription> findByUser(User user);

    /**
     * Searches in database a subscription given a user id and a topic id. 
     * @param userId
     * @param topicId
     * @return Optional<Subscription>
     */
    public Optional<Subscription> findByUserIdAndTopicId(Long userId, Long topicId);
    
}
