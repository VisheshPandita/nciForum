package com.vishesh.nciForum.repository;

import com.vishesh.nciForum.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository class for Topic
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    /**
     * Find all topics by order of created at
     * @param pageable
     * @return Page of topics
     */
    Page<Topic> findAllByOrderByCreatedAtDesc(Pageable pageable);

    /**
     * Find topic by name
     * @param name
     * @return Optional of topic
     */
    Optional<Topic> findTopicByName(String name);
}
