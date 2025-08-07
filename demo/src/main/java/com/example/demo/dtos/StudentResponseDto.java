package com.example.demo.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private List<CourseResponseDto> courses;
    private DepartmentResponseDto department;
}
