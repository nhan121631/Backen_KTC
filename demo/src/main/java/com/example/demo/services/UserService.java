package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.Auth.RoleResponseDto;
import com.example.demo.dtos.UserProfileResponDto;
import com.example.demo.dtos.Auth.LoginRequestDto;
import com.example.demo.dtos.Auth.LoginResponseDto;
import com.example.demo.dtos.Auth.UserResponseDto;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.exceptions.HttpException;
import com.example.demo.repositories.UserJpaRepository;

@Service
public class UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private JwtService jwtService;

    private RoleResponseDto convertToRoleDto(Role role) {
        return RoleResponseDto.builder()
                .id(role.getId())
                .code(role.getCode())
                .name(role.getName())
                .build();
    }

    private UserResponseDto convertToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles() != null ? user.getRoles().stream()
                        .map(this::convertToRoleDto)
                        .toList() : null)
                .build();
    }

    public UserResponseDto getUserByUsername(String username) {
        User user = userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    public LoginResponseDto login(LoginRequestDto request) throws Exception {
        // Find the user by email (username)
        User user = this.userJpaRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new HttpException("Invalid username or password", HttpStatus.UNAUTHORIZED));

        // Verify password
        if (!request.getPassword().equals(user.getPassword())) {
            throw new HttpException("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }

        // Generate a new access token (with full data + roles)
        String accessToken = jwtService.generateAccessToken(user);
        // Generate a refresh token
        String refreshToken = jwtService.generateRefreshToken(user);

        return LoginResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userProfile(user.getUserProfile() != null ? UserProfileResponDto.builder()
                        .fullName(user.getUserProfile().getFullName())
                        .email(user.getUserProfile().getEmail())
                        .address(user.getUserProfile().getAddress())
                        .build() : null)
                .roles(user.getRoles() != null
                        ? user.getRoles().stream().map(Role::getName).toList()
                        : null)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
