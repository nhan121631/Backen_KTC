package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CreateStudentRequestDto;
import com.example.demo.dtos.StudentPageResponseDto;
import com.example.demo.dtos.StudentResponseDto;
import com.example.demo.dtos.UpdateStudentRequestDto;
import com.example.demo.repositories.StudentProjection;
import com.example.demo.services.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    // @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponseDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/paging")
    public StudentPageResponseDto getStudentPaginated(
            @RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        return studentService.getStudentPaginated(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public StudentResponseDto createStudent(@RequestBody @Valid CreateStudentRequestDto createStudentRequestDto) {
        return studentService.createStudent(createStudentRequestDto);
    }

    @PatchMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable("id") Long id,
            @RequestBody @Valid UpdateStudentRequestDto updateStudentRequestDto) {
        return studentService.updateStudent(id, updateStudentRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/email/{email}")
    public StudentProjection getStudentByEmail(@PathVariable("email") String email) {
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("/name/{name}")
    public StudentResponseDto getStudentByName(@PathVariable("name") String name) {
        return studentService.getStudentByName(name);
    }

}
