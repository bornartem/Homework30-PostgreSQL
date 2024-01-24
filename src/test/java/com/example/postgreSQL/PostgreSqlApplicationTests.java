package com.example.postgreSQL;

import com.example.postgreSQL.controller.StudentController;
import com.example.postgreSQL.model.Faculty;
import com.example.postgreSQL.model.Student;
import com.example.postgreSQL.repositories.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.*;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostgreSqlApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    //    @Test
//    void testID() throws Exception {
//        Student student = new Student(long id, string name, int age, null);
//        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/id=1", Student.class))
//                .isNotNull();
//    }
    @Test
    void getStudentsTest() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotEmpty();
    }

    @Test
    void createStudentTest() throws Exception {
       Student student = new Student(1L, "Artem", 34, null);
        Assertions.assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
//        assertEquals("Artem", student.getName());
//        Assertions.assertThat(this.testRestTemplate.delete("http://localhost:" + port + "/student", student, Student.class);
    }

    @Test
    void deleteStudentTest() throws Exception{
        Student student = new Student(1L, "Artem", 34, null);
        Student createdStudent = testRestTemplate.postForObject("/student", student, Student.class);

        testRestTemplate.delete("/users/" + createdStudent.getId());

        ResponseEntity<Student> response = testRestTemplate.getForEntity("/student/" + createdStudent.getId(), Student.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        Assertions.assertThat(this.testRestTemplate.delete("http://localhost:" + port + "/student/id=452", Student.class))
    }


}
