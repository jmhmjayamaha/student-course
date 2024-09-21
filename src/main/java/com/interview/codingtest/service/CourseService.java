package com.interview.codingtest.service;

import org.springframework.data.domain.Page;

import com.interview.codingtest.dto.CreateUpdateCourseRequest;
import com.interview.codingtest.dto.CreateUpdateCourseResponse;
import com.interview.codingtest.entity.Course;

public interface CourseService {

    CreateUpdateCourseResponse createCourse(CreateUpdateCourseRequest request);

    CreateUpdateCourseResponse updateCourse(Long couseId, CreateUpdateCourseRequest request);

    void deleteCourse();

    Page<Course> retrieveCourses(int page, int size);

    Course retrieveStudent(Long courseId);
}
