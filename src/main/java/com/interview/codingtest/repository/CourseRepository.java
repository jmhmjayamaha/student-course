package com.interview.codingtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interview.codingtest.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
