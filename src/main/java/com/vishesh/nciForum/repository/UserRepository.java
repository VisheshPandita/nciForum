package com.vishesh.nciForum.repository;

import java.util.Optional;

import com.vishesh.nciForum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for User
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * Find user by username
   * @param username
   * @return Optional of user
   */
  Optional<User> findByUsername(String username);

}
