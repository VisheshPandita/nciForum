package com.vishesh.nciForum.controller;

import com.vishesh.nciForum.entity.AuthenticationRequest;
import com.vishesh.nciForum.entity.AuthenticationResponse;
import com.vishesh.nciForum.service.AuthenticationService;
import com.vishesh.nciForum.entity.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Controller class for Authentication
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final LogoutHandler logoutHandler;

  @PostMapping("/logout")
  public ResponseEntity<String> performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
    logoutHandler.logout(request, response, authentication);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(authenticationService.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    authenticationService.refreshToken(request, response);
  }
}
