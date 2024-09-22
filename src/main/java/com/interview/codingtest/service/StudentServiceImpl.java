package com.interview.codingtest.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.interview.codingtest.dto.CreateStudentResponse;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.dto.StudentDTO;
import com.interview.codingtest.entity.Course;
import com.interview.codingtest.entity.Student;
import com.interview.codingtest.exception.NotFoundException;
import com.interview.codingtest.mapper.StudentMapper;
import com.interview.codingtest.repository.CourseRepository;
import com.interview.codingtest.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    private CourseRepository courseRepository;

    private StudentMapper studentMapper;

    @Override
    public CreateStudentResponse createStudent(CreateUpdateStudentReqeust request) {

        Student student = studentMapper.toEntity(request);
        
        if (request.getCourseIds() != null) {
            Set<Course> courses = request.getCourseIds().stream()
                    .map(couresId -> courseRepository.findById(couresId).orElse(null)).filter(course -> course != null)
                    .collect(Collectors.toSet());
            student.setCourses(courses);
        }
        
        student = studentRepository.save(student);
        
        return new CreateStudentResponse(student.getId());

    }

    @Override
    public StudentDTO updateStudent(Long studentId, CreateUpdateStudentReqeust request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found for the given id : " + studentId));

        if (request.getCourseIds() != null) {
            Set<Course> courses = request.getCourseIds().stream()
                    .map(couresId -> courseRepository.findById(couresId).orElse(null)).filter(course -> course != null)
                    .collect(Collectors.toSet());
            student.setCourses(courses);
        }

        studentMapper.updateStudentFromDTO(request, student);

        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toDto(updatedStudent);
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
    public Page<StudentDTO> retrieveStudents(int page, int size) {

        Page<Student> students = studentRepository.findAll(PageRequest.of(page, size));

        return students.map(student -> studentMapper.toDto(student));

    }

    @Override
    public StudentDTO retrieveStudent(Long studentId) {
        
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found for the given id : " + studentId));
        
        return studentMapper.toDto(student);
    }

}
