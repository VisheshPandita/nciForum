package com.vishesh.nciForum.repository;

import com.vishesh.nciForum.model.Comment;
import com.vishesh.nciForum.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository class for Comment
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    /**
     * Find comments by discussion id
     * @param discussionId
     * @return List of comments
     */
    List<Comment> findCommentsByDiscussionIdOrderByCreatedAtDesc(UUID discussionId);

    /**
     * Delete comments by discussion
     * @param existingDiscussion
     */
    void deleteByDiscussion(Discussion existingDiscussion);
}
