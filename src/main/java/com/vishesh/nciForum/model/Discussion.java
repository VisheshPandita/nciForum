package com.vishesh.nciForum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Model class for Discussion
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "discussion_id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "discussion_title", nullable = false)
    @NotNull
    private String title;
    @Column(name = "discussion_content", nullable = false)
    @NotNull
    @Lob
    private String content;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private User author;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "topic_id")
    @NotNull
    private Topic topic;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
