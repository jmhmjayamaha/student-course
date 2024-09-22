package com.interview.codingtest.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.codingtest.dto.CreateUpdateCourseRequest;
import com.interview.codingtest.entity.Course;
import com.interview.codingtest.repository.CourseRepository;

@TestPropertySource("/application-test.properties")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CourseControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private CourseRepository courseRepository;

    @DisplayName("Create course API test.")
    @Nested
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class CreateCourseAPITest {

        private CreateUpdateCourseRequest createReqeust;

        @BeforeEach
        void beforeEach() {
            createReqeust = new CreateUpdateCourseRequest();
            createReqeust.setName("C#");
            createReqeust.setFee(1000.00);
        }

        @DisplayName("Create course : create course")
        @Test
        @Order(1)
        void createStudent() throws JsonProcessingException, Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/courses").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createReqeust))).andExpect(status().isCreated());

            Course course = courseRepository.findById(1L).get();

            assertNotNull(course, "Course should not be valid.");
        }
    }
}
