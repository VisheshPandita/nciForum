package com.vishesh.nciForum.controller;

import com.vishesh.nciForum.entity.ChangePasswordRequest;
import com.vishesh.nciForum.entity.UserRequest;
import com.vishesh.nciForum.model.User;
import com.vishesh.nciForum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controller class for User
 */

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping
    public ResponseEntity<Void> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUserByUsername(@PathVariable String username, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUserByUsername(username, userRequest));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{username}/update-password")
    public ResponseEntity<Void> updatePassword(@PathVariable String username, @RequestBody ChangePasswordRequest request) {
        userService.updatePasswordByUsername(username, request);
        return ResponseEntity.ok().build();
    }
}
