package com.vishesh.nciForum.repository;

import java.util.List;
import java.util.Optional;

import com.vishesh.nciForum.model.Token;
import com.vishesh.nciForum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository class for Token
 */
public interface TokenRepository extends JpaRepository<Token, Integer> {

  /**
   * Find all valid tokens by user
   * @param id
   * @return List of tokens
   */
  @Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(Integer id);

  /**
   * Find token by token
   * @param token
   * @return Optional of token
   */
  Optional<Token> findByToken(String token);

  void deleteByUser(User user);
}
