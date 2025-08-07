package com.example.demo.dtos.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponseDto {
    private Long id;
    private String code;
    private String name;
}
