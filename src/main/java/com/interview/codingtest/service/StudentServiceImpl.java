package com.interview.codingtest.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.interview.codingtest.dto.CreateStudentResponse;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.entity.Student;
import com.interview.codingtest.exception.NotFoundException;
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
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found for the given id : " + studentId));

        studentRepository.delete(student);

        if (studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not deleted");
        }
    }

    @Override
    public Page<Student> retrieveStudents(int page, int size) {

        Page<Student> students = studentRepository.findAll(PageRequest.of(page, size));

        return students;
    }

    @Override
    public Student retrieveStudent(Long studentId) {
        
        Optional<Student> student = studentRepository.findById(studentId);
        
        return student.orElseThrow(() -> new NotFoundException("Student not found for the given id : " + studentId));
    }

}
