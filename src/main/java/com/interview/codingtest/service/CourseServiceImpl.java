package com.interview.codingtest.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.interview.codingtest.dto.CreateUpdateCourseRequest;
import com.interview.codingtest.dto.CreateUpdateCourseResponse;
import com.interview.codingtest.entity.Course;
import com.interview.codingtest.mapper.CourseMapper;
import com.interview.codingtest.repository.CourseRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    private CourseMapper courseMapper;

    @Override
    public CreateUpdateCourseResponse createCourse(CreateUpdateCourseRequest request) {

        Course course = courseRepository.save(courseMapper.toEntity(request));

        return new CreateUpdateCourseResponse(course.getId());
    }

    @Override
    public CreateUpdateCourseResponse updateCourse(Long couseId, CreateUpdateCourseRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCourse() {
        // TODO Auto-generated method stub

    }

    @Override
    public Page<Course> retrieveCourses(int page, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Course retrieveStudent(Long courseId) {
        // TODO Auto-generated method stub
        return null;
    }

}
