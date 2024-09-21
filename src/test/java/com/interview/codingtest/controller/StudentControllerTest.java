package com.interview.codingtest.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.codingtest.dto.CreateUpdateStudentReqeust;
import com.interview.codingtest.entity.Student;
import com.interview.codingtest.repository.StudentRepository;

@TestPropertySource("/application-test.properties")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@SpringBootTest
public class StudentControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @DisplayName("Crate student API test.")
    @Nested
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class CreateStudentAPITest {

        private CreateUpdateStudentReqeust studentRequest;

        @BeforeEach
        void beforeEach() {
            studentRequest = new CreateUpdateStudentReqeust();

            studentRequest.setFirstName("Harshana");
            studentRequest.setLastName("Jayamaha");
            studentRequest.setAge(35);
            studentRequest.setDob(LocalDate.of(1989, 04, 9));
            studentRequest.setTelNo("0774543727");
            studentRequest.setEmail("test@gmail.com");
        }

        @DisplayName("Create student : create student")
        @Test
        @Order(1)
        public void createStudent() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.post("/api/students").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(studentRequest))).andExpect(status().isCreated());

            Student sutdent = studentRepository.findByEmail("test@gmail.com").get();

            assertNotNull(sutdent, "Student should not be valid.");
        }

        @DisplayName("Create student: null constraints")
        @ParameterizedTest
        @ValueSource(strings = {"firstName", "lastName", "nic", "telNo", "email"})
        @Order(2)
        public void createStudentNotNullContraint(String input) throws Exception {

            switch (input) {
                case "firstName":
                    studentRequest.setFirstName(null);
                    break;
                case "lastName":
                    studentRequest.setLastName(null);
                    break;
                case "nic":
                    studentRequest.setNic(null);
                    break;
                case "telNo":
                    studentRequest.setTelNo(null);
                    break;
                case "email":
                    studentRequest.setEmail(null);
                    break;
            }

            mockMvc.perform(MockMvcRequestBuilders.post("/api/students").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(studentRequest)))
                    .andExpect(status().is4xxClientError());

            Student sutdent = studentRepository.findByEmail("test@gmail.com").get();

            assertNotNull(sutdent, "Student should not be valid.");
        }
    }

    @Test
    void test() {

    }
}
