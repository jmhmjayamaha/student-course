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

import com.interview.codingtest.dto.CourseDTO;
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

    @GetMapping
    public Page<CourseDTO> retrieveCourses(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Retrieve courses.");

        Page<CourseDTO> response = courseService.retrieveCourses(page, size);

        log.info("Retrieve courses done.");

        return response;
    }

    @GetMapping("/{course-id}")
    public CourseDTO retrieveStudents(@PathVariable("course-id") Long courseId) {
        log.info("Retrieve student.");

        CourseDTO response = courseService.retrieveCourse(courseId);

        log.info("Retrieve students done.");

        return response;
    }

    @DeleteMapping("/{course-id}")
    public void deleteStudent(@PathVariable("course-id") Long courseId) {
        log.info("Delete student.");

        courseService.deleteCourse(courseId);

        log.info("Delete student done.");
    }

    @PutMapping("/{course-id}")
    public CourseDTO udpateCourse(@PathVariable("course-id") Long courseId,
            @RequestBody @Validated CreateUpdateCourseRequest request) {

        log.info("Update course.");

        CourseDTO response = courseService.updateCourse(courseId, request);

        log.info("Update course done.");

        return response;
    }

    @PostMapping("/external")
    public CourseDTO saveExternalCourses(@RequestParam(value = "id") String id) {
        log.info("Create course from external source.");

        CourseDTO response = courseService.createExternalCourse(id);

        log.info("Create course from external source done.");

        return response;
    }
}
