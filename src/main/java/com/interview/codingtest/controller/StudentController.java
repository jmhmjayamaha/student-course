package com.interview.codingtest.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.interview.codingtest.dto.CreateStudentResponse;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.dto.StudentDTO;
import com.interview.codingtest.service.StudentService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@Slf4j
public class StudentController {

    private StudentService studentService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateStudentResponse createStudent(@RequestBody @Validated CreateUpdateStudentReqeust request) {

        log.info("Create student.");

        CreateStudentResponse response = studentService.createStudent(request);

        log.info("Create student done.");

        return response;
    }

    @GetMapping
    public Page<StudentDTO> retrieveStudents(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Retrieve students.");

        Page<StudentDTO> response = studentService.retrieveStudents(page, size);

        log.info("Retrieve students done.");

        return response;
    }

    @GetMapping("/{student-id}")
    public StudentDTO retrieveStudents(@PathVariable("student-id") Long studentId) {
        log.info("Retrieve student.");

        StudentDTO response = studentService.retrieveStudent(studentId);

        log.info("Retrieve students done.");

        return response;
    }

    @DeleteMapping("/{student-id}")
    public void deleteStudent(@PathVariable("student-id") Long studentId) {
        log.info("Delete student.");

        studentService.deleteStudent(studentId);

        log.info("Delete student done.");
    }

    @PutMapping("/{student-id}")
    public StudentDTO updateStudent(@PathVariable("student-id") Long studentId,
            @RequestBody @Validated CreateUpdateStudentReqeust request) {

        log.info("Update course.");

        StudentDTO response = studentService.updateStudent(studentId, request);

        log.info("Update course done.");

        return response;
    }
}
