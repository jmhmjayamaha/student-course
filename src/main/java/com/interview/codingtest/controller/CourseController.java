package com.interview.codingtest.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<CreateUpdateCourseResponse> createCourse(
            @RequestBody @Validated CreateUpdateCourseRequest request) {

        log.info("Create course.");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        CreateUpdateCourseResponse response = courseService.createCourse(request);

        log.info("Create course done.");

        return new ResponseEntity<CreateUpdateCourseResponse>(response, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<CourseDTO>> retrieveCourses(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Retrieve courses.");

        Page<CourseDTO> response = courseService.retrieveCourses(page, size);

        log.info("Retrieve courses done.");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<CourseDTO> retrieveStudents(@PathVariable("course-id") Long courseId) {
        log.info("Retrieve student.");

        CourseDTO response = courseService.retrieveCourse(courseId);

        log.info("Retrieve students done.");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{course-id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("course-id") Long courseId) {
        log.info("Delete student.");

        courseService.deleteCourse(courseId);

        log.info("Delete student done.");

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{course-id}")
    public ResponseEntity<CourseDTO> udpateCourse(@PathVariable("course-id") Long courseId,
            @RequestBody @Validated CreateUpdateCourseRequest request) {

        log.info("Update course.");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{course-id}")
                .buildAndExpand(courseId).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        CourseDTO response = courseService.updateCourse(courseId, request);

        log.info("Update course done.");

        return new ResponseEntity<CourseDTO>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/external")
    public ResponseEntity<CreateUpdateCourseResponse> saveExternalCourses(@RequestParam(value = "id") String id) {
        log.info("Create course from external source.");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        CreateUpdateCourseResponse response = courseService.createExternalCourse(id);

        log.info("Create course from external source done.");

        return new ResponseEntity<CreateUpdateCourseResponse>(response, headers, HttpStatus.CREATED);
    }
}
