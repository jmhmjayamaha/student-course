package com.interview.codingtest.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.interview.codingtest.dto.CreateStudentResponse;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.entity.Student;
import com.interview.codingtest.mapper.StudentMapper;
import com.interview.codingtest.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    private StudentMapper studentMapper;

    @Override
    public CreateStudentResponse createStudent(CreateUpdateStudentReqeust request) {

        Student student = studentRepository.save(studentMapper.toEntity(request));

        return new CreateStudentResponse(student.getId());

    }

    @Override
    public Student updateStudent(String studentId, CreateUpdateStudentReqeust request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean deleteStudent(String studentId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Page<Student> retrieveAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Student retrieveStudent(String studentId) {
        // TODO Auto-generated method stub
        return null;
    }

}
