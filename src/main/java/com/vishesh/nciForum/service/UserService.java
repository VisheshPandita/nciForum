package com.vishesh.nciForum.service;

import com.vishesh.nciForum.entity.ChangePasswordRequest;
import com.vishesh.nciForum.entity.UserRequest;
import com.vishesh.nciForum.exception.CustomException;
import com.vishesh.nciForum.model.User;
import com.vishesh.nciForum.repository.TokenRepository;
import com.vishesh.nciForum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    public User findByUsername(String creatorUsername) {
        return userRepository.findByUsername(creatorUsername).orElseThrow(() -> new CustomException("User not found", "USER_NOT_FOUND", 404));
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException("User not found", "USER_NOT_FOUND", 404));
    }

    public User updateUserByUsername(String username, UserRequest userRequest) {
        User user = findByUsername(username);
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserByUsername(String username) {
        User user = findByUsername(username);
        tokenRepository.deleteByUser(user);
        userRepository.delete(user);
    }

    public void updatePasswordByUsername(String username, ChangePasswordRequest request) {
        User user = findByUsername(username);
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new CustomException("Wrong password", "WRONG_PASSWORD", 400);
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new CustomException("Passwords are not the same", "PASSWORDS_NOT_SAME", 400);
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
