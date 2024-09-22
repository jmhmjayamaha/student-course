package com.interview.codingtest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.interview.codingtest.dto.CourseApiResponse;
import com.interview.codingtest.dto.CourseDTO;
import com.interview.codingtest.dto.CreateUpdateCourseRequest;
import com.interview.codingtest.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toEntity(CreateUpdateCourseRequest request);

    CourseDTO toDto(Course course);

    void updateCourseFromDTO(CreateUpdateCourseRequest request, @MappingTarget Course course);

    @Mapping(source = "courseName", target = "name")
    @Mapping(source = "courseFee", target = "fee")
    Course toEntityFromExternalCourse(CourseApiResponse response);

}
