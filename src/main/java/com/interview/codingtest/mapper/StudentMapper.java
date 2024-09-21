package com.interview.codingtest.mapper;

import org.mapstruct.Mapper;

import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.dto.StudentDTO;
import com.interview.codingtest.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(CreateUpdateStudentReqeust request);

    StudentDTO toDto(Student student);

}
