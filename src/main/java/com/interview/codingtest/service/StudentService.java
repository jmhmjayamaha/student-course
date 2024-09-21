package com.interview.codingtest.service;

import org.springframework.data.domain.Page;

import com.interview.codingtest.dto.CreateStudentResponse;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.dto.StudentDTO;

public interface StudentService {

    CreateStudentResponse createStudent(CreateUpdateStudentReqeust request);

    StudentDTO updateStudent(String studentId, CreateUpdateStudentReqeust request);

    void deleteStudent(Long studentId);

    Page<StudentDTO> retrieveStudents(int page, int size);

    StudentDTO retrieveStudent(Long studentId);

}
