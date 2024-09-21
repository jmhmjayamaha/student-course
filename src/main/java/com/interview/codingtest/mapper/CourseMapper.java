package com.interview.codingtest.mapper;

import org.mapstruct.Mapper;

import com.interview.codingtest.dto.CreateUpdateCourseRequest;
import com.interview.codingtest.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course toEntity(CreateUpdateCourseRequest request);

}
