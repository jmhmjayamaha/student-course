package com.interview.codingtest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.dto.StudentDTO;
import com.interview.codingtest.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(CreateUpdateStudentReqeust request);

    StudentDTO toDto(Student student);

    void updateStudentFromDTO(CreateUpdateStudentReqeust request, @MappingTarget Student course);

}
