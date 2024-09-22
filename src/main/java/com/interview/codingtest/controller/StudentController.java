package com.interview.codingtest.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.interview.codingtest.dto.CreateStudentResponse;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.dto.StudentDTO;
import com.interview.codingtest.service.StudentService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@Slf4j
public class StudentController {

    private StudentService studentService;

    @PostMapping
    public ResponseEntity<CreateStudentResponse> createStudent(
            @RequestBody @Validated CreateUpdateStudentReqeust request) {

        log.info("Create student.");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        CreateStudentResponse response = studentService.createStudent(request);

        log.info("Create student done.");

        return new ResponseEntity<CreateStudentResponse>(response, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<StudentDTO>> retrieveStudents(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Retrieve students.");

        Page<StudentDTO> response = studentService.retrieveStudents(page, size);

        log.info("Retrieve students done.");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<StudentDTO> retrieveStudents(@PathVariable("student-id") Long studentId) {
        log.info("Retrieve student.");

        StudentDTO response = studentService.retrieveStudent(studentId);

        log.info("Retrieve students done.");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("student-id") Long studentId) {
        log.info("Delete student.");

        studentService.deleteStudent(studentId);

        log.info("Delete student done.");

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("student-id") Long studentId,
            @RequestBody @Validated CreateUpdateStudentReqeust request) {

        log.info("Update course.");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{student-id}").buildAndExpand(studentId)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        StudentDTO response = studentService.updateStudent(studentId, request);

        log.info("Update course done.");

        return new ResponseEntity<StudentDTO>(response, headers, HttpStatus.OK);
    }
}
