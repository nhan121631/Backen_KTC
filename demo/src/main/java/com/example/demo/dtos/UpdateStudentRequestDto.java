package com.example.demo.dtos;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequestDto {

    private String name;

    @Email(message = "Email should be valid")
    private String email;

    private String address;

    private String password;

}
