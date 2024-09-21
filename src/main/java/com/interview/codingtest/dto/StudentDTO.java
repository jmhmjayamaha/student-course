package com.interview.codingtest.dto;

import java.time.LocalDate;
import java.util.Set;

import com.interview.codingtest.entity.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

    private String firstName;

    private String middleName;

    private String lastName;

    private int age;

    private LocalDate dob;

    private String nic;

    private String telNo;

    private String email;

    private Address address;

    private Set<CourseDTO> courses;
}
