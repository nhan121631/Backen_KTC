package com.example.demo.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponDto {
    private String fullName;
    private String email;
    private String address;
}
