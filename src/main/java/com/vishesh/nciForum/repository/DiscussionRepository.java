package com.vishesh.nciForum.repository;

import com.vishesh.nciForum.model.Discussion;
import com.vishesh.nciForum.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository class for Discussion
 */
@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, UUID> {

    /**
     * Find all discussions by order of created at
     * @param pageable
     * @return Page of discussions
     */
    Page<Discussion> findAllByOrderByCreatedAtDesc(Pageable pageable);

    /**
     * Find discussions by topic
     * @param topic
     * @param pageable
     * @return Page of discussions
     */
    Page<Discussion> findDiscussionsByTopicOrderByCreatedAtDesc(Topic topic, Pageable pageable);
}
