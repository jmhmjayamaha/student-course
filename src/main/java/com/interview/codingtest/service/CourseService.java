package com.interview.codingtest.service;

import org.springframework.data.domain.Page;

import com.interview.codingtest.dto.CourseDTO;
import com.interview.codingtest.dto.CreateUpdateCourseRequest;
import com.interview.codingtest.dto.CreateUpdateCourseResponse;

public interface CourseService {

    CreateUpdateCourseResponse createCourse(CreateUpdateCourseRequest request);

    CourseDTO updateCourse(Long couseId, CreateUpdateCourseRequest request);

    void deleteCourse(Long courseId);

    Page<CourseDTO> retrieveCourses(int page, int size);

    CourseDTO retrieveCourse(Long courseId);

    CourseDTO createExternalCourse(String id);

}
