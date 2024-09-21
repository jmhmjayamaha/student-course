package com.interview.codingtest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.interview.codingtest.dto.CreateUpdateCourseRequest;
import com.interview.codingtest.dto.CreateUpdateCourseResponse;
import com.interview.codingtest.service.CourseService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@Slf4j
public class CourseController {

    private CourseService courseService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateUpdateCourseResponse createCourse(@RequestBody @Validated CreateUpdateCourseRequest request) {

        log.info("Create course.");

        CreateUpdateCourseResponse response = courseService.createCourse(request);

        log.info("Create course done.");

        return response;
    }
}
