package com.interview.codingtest.service;

import org.springframework.data.domain.Page;

import com.interview.codingtest.dto.CreateStudentResponse;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.entity.Student;

public interface StudentService {

    CreateStudentResponse createStudent(CreateUpdateStudentReqeust request);

    Student updateStudent(String studentId, CreateUpdateStudentReqeust request);

    boolean deleteStudent(Long studentId);

    Page<Student> retrieveStudents(int page, int size);

    Student retrieveStudent(Long studentId);

}
