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
 * Model class for Topic
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "topic_id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "topic_name", nullable = false)
    @NotNull
    private String name;
    @Column(name = "topic_description", nullable = false)
    @NotNull
    private String description;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @NotNull
    private List<User> moderators;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Discussion> discussions;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
