package com.interview.codingtest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.interview.codingtest.dto.CreateStudentResponse;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
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

        log.info("Create student");

        CreateStudentResponse response = studentService.createStudent(request);

        log.info("Create student done");

        return response;
    }
}
