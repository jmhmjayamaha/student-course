package com.interview.codingtest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.interview.codingtest.dto.CourseApiResponse;
import com.interview.codingtest.dto.CourseDTO;
import com.interview.codingtest.dto.CreateUpdateCourseRequest;
import com.interview.codingtest.dto.CreateUpdateCourseResponse;
import com.interview.codingtest.entity.Course;
import com.interview.codingtest.exception.NotFoundException;
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

    private ApiService apiService;

    @Override
    public CreateUpdateCourseResponse createCourse(CreateUpdateCourseRequest request) {

        Course course = courseRepository.save(courseMapper.toEntity(request));

        return new CreateUpdateCourseResponse(course.getId());
    }

    @Override
    public CourseDTO updateCourse(Long courseId, CreateUpdateCourseRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found for the given id : " + courseId));

        courseMapper.updateCourseFromDTO(request, course);

        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found for the given id : " + courseId));

        courseRepository.delete(course);

        if (courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course not deleted");
        }

    }

    @Override
    public Page<CourseDTO> retrieveCourses(int page, int size) {
        Page<Course> courses = courseRepository.findAll(PageRequest.of(page, size));

        return courses.map(course -> courseMapper.toDto(course));
    }

    @Override
    public CourseDTO retrieveCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found for the given id : " + courseId));

        return courseMapper.toDto(course);
    }

    @Override
    public CreateUpdateCourseResponse createExternalCourse(String id) {
        log.info("Calling external API");

        CourseApiResponse externalCourse = apiService.callExternalApi(id);

        log.info("Calling external API done.");

        Course course = courseRepository.save(courseMapper.toEntityFromExternalCourse(externalCourse));

        return new CreateUpdateCourseResponse(course.getId());
    }

}
