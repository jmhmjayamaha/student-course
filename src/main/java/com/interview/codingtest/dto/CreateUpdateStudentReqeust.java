package com.interview.codingtest.dto;

import java.time.LocalDate;
import java.util.Set;

import com.interview.codingtest.entity.Address;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUpdateStudentReqeust {

    @NotEmpty
    private String firstName;

    private String middleName;

    @NotEmpty
    private String lastName;

    @Min(value = 15)
    private int age;

    private LocalDate dob;

    @NotEmpty
    private String nic;

    @NotEmpty
    private String telNo;

    @NotEmpty
    private String email;

    private Address address;

    private Set<String> courseIds;

}
