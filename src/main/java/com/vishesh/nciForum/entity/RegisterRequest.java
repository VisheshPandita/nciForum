package com.vishesh.nciForum.entity;

import com.vishesh.nciForum.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String username;
  private String email;
  private String password;
  private Role role;
}
