package com.interview.codingtest.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private JdbcTemplate jdbc;

    @Autowired
    private StudentRepository studentRepository;

    @DisplayName("Crate student API test.")
    @Nested
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class CreateStudentAPITest {

        private CreateUpdateStudentReqeust studentRequest;

        @Value("${sql.script.create.student}")
        private String sqlAddStudent;

        @Value("${sql.script.create.address}")
        private String sqlAddAddress;

        @Value("${sql.script.delete.address}")
        private String sqlDeleteAddress;

        @Value("${sql.script.delete.student}")
        private String sqlDeleteStudent;

        @BeforeEach
        void beforeEach() {
            studentRequest = new CreateUpdateStudentReqeust();

            studentRequest.setFirstName("Harshana");
            studentRequest.setLastName("Jayamaha");
            studentRequest.setAge(35);
            studentRequest.setDob(LocalDate.of(1989, 04, 9));
            studentRequest.setTelNo("0774543727");
            studentRequest.setEmail("test@gmail.com");
            studentRequest.setNic("198920000312");

            jdbc.execute(sqlAddStudent);
        }

        @DisplayName("Create student : create student")
        @Test
        @Order(1)
        void createStudentTest() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.post("/api/students").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(studentRequest))).andExpect(status().isCreated());

            Student sutdent = studentRepository.findByEmail("test@gmail.com").get();

            assertNotNull(sutdent, "Student should not be valid.");

        }

        @DisplayName("Create student: empty/null constraints")
        @ParameterizedTest
        @ValueSource(strings = {"firstName", "lastName", "nic", "telNo", "email"})
        @Order(2)
        void createStudentNotNullContraintTest(String input) throws Exception {

            String message = input + " : must not be empty";

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
                    .andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("$..errorCode").value(HttpStatus.BAD_REQUEST.value()))
                    .andExpect(jsonPath("$..errorMessage").value(message));

        }

        @DisplayName("Create student: data constraints violation.")
        @ParameterizedTest
        @ValueSource(strings = {"tellNo", "email", "nic"})
        @Order(3)
        void createStudentDataContraintViolationTest(String input) throws Exception {

            switch (input) {
                case "tellNo":
                    studentRequest.setTelNo("0771778211");
                    break;
                case "email":
                    studentRequest.setEmail("contraint.violation@gmail.com");
                    break;
                case "nic":
                    studentRequest.setNic("200020000321");
                    break;
            }

            mockMvc.perform(MockMvcRequestBuilders.post("/api/students").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(studentRequest))).andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("$..errorCode").value(HttpStatus.CONFLICT.value()));

        }

        @AfterEach
        void afterEach() {
            jdbc.execute(sqlDeleteStudent);
        }
    }

    @DisplayName("Retrieve students API test.")
    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RetrieveStuentsAPITest {

        @DisplayName("Retrieve students: retrive all")
        @Test
        @Order(1)
        void retrieveAllStudentTest() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get("/api/students").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.totalElements").value(11))
                    .andExpect(jsonPath("$.numberOfElements").value(10));
        }

        @DisplayName("Retrieve students: retrive page 0 and size 5")
        @Test
        @Order(2)
        void retrieveStudentPaginationTest() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get("/api/students").param("page", "0").param("size", "5")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(jsonPath("$.totalElements").value(11))
                    .andExpect(jsonPath("$.numberOfElements").value(5));
        }
    }

    @DisplayName("Retrieve student API test.")
    @Nested
    @Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RetreiveStudentAPITest {

        @DisplayName("Retrieve student : retrieve student")
        @Test
        @Order(1)
        void retriveStudentTest() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/students/10011")
                    .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                    .andExpect(jsonPath("$.email").value("test01@gmail.com"));
        }

        @DisplayName("Retrieve student : student not found")
        @Test
        @Order(1)
        void retrieveStudentNotFoundTest() throws Exception {
            String studentId = "10011111";
            
            mockMvc.perform(MockMvcRequestBuilders.get("/api/students/".concat(studentId))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("$.errorCode").value(HttpStatus.NOT_FOUND.value()))
                    .andExpect(jsonPath("$.errorMessage")
                            .value("Student not found for the given id : ".concat(studentId)));
        }

    }

    @DisplayName("Delete student API test.")
    @Nested
    @Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class DeleteStudentAPITest {

        @DisplayName("Delete student : delete student")
        @Test
        @Order(1)
        void retriveStudentTest() throws Exception {
            mockMvc.perform(
                    MockMvcRequestBuilders.delete("/api/students/10011").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @DisplayName("Delete student : student not found")
        @Test
        @Order(2)
        void retriveStudentNotFoundTest() throws Exception {
            String studentId = "10011111";

            mockMvc.perform(
                    MockMvcRequestBuilders.delete("/api/students/".concat(studentId))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is4xxClientError())
                    .andExpect(jsonPath("$.errorCode").value(HttpStatus.NOT_FOUND.value()))
                    .andExpect(jsonPath("$.errorMessage")
                            .value("Student not found for the given id : ".concat(studentId)));
        }
    }

}
