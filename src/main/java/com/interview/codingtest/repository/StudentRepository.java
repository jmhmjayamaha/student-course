package com.interview.codingtest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.codingtest.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
}
