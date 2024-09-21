package com.interview.codingtest.service;

import org.springframework.data.domain.Page;

import com.interview.codingtest.dto.CreateStudentResponse;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.entity.Student;

public interface StudentService {

    CreateStudentResponse createStudent(CreateUpdateStudentReqeust request);

    Student updateStudent(String studentId, CreateUpdateStudentReqeust request);

    boolean deleteStudent(String studentId);

    Page<Student> retrieveAll();

    Student retrieveStudent(String studentId);

}
