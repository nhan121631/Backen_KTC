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
public class StudentPageResponseDto {
    private List<StudentResponseDto> data;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalRecords;
    private Boolean hasNext;
    private Boolean hasPrevious;
}