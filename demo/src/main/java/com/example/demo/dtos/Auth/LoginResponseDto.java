package com.example.demo.dtos.Auth;

import java.util.List;

import com.example.demo.dtos.UserProfileResponDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDto {
    Long id;
    String username;
    List<String> roles;
    UserProfileResponDto userProfile;
    String accessToken;
    String refreshToken;

}