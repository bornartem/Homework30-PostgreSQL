package com.example.postgreSQL;

import com.example.postgreSQL.controller.FacultyController;
import com.example.postgreSQL.controller.StudentController;
import com.example.postgreSQL.model.Faculty;
import com.example.postgreSQL.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostgreSqlApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;
    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
        assertThat(facultyController).isNotNull();
    }

    @Test
    void readStudentTest() throws Exception {
        Student student = new Student(60L, "Artem", 3, null);
        restTemplate.postForObject("http://localhost:" + port + "/student/post", student, Student.class);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + student.getId(), Student.class))
                .isEqualTo(student);
        assertThat(student).isNotNull();
    }

    @Test
    void readFacultyTest() throws Exception {
        Faculty faculty = new Faculty(5L, "Test", "Test1", null);
        restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/1", Faculty.class))
                .isEqualTo(faculty);
        assertThat(faculty).isNotNull();
    }

    @Test
    void getStudentsTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotEmpty();
    }

    @Test
    void getFacultyTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty", String.class))
                .isNotEmpty();
    }

    @Test
    void createStudentTest() throws Exception {
        Student student = new Student(51L, "Artem", 34, null);
        var result = restTemplate.postForObject("http://localhost:" + port + "/student/post", student, Student.class);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Artem");
    }

    @Test
    void createFacultyTest() throws Exception {
        Faculty faculty = new Faculty(5L, "Test", "Test1", null);
        var result = restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Test");
    }

    @Test
    void updateStudentTest() throws Exception {
        Student student = new Student(60L, "Artem", 3, null);
        restTemplate.postForObject("http://localhost:" + port + "/student/post", student, Student.class);
        Student updated = new Student(60L, "Person", 33, null);
        ResponseEntity<Student> tested = restTemplate.exchange("http://localhost:" + port + "/student/update",
                HttpMethod.PUT,
                new HttpEntity<>(updated),
                Student.class);
        assertThat(tested.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(tested.getBody().getName()).isEqualTo(updated.getName());
    }

    @Test
    void updateFacultyTest() throws Exception {
        Faculty faculty = new Faculty(5L, "Test", "Test1", null);
        restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, Faculty.class);
        Faculty updated = new Faculty(5L, "Person", "test", null);
        ResponseEntity<Faculty> tested = restTemplate.exchange("http://localhost:" + port + "/faculty",
                HttpMethod.PUT,
                new HttpEntity<>(updated),
                Faculty.class);
        assertThat(tested.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(tested.getBody().getColor()).isEqualTo(updated.getColor());
    }

    @Test
    void deleteStudentTest() throws Exception {
        Student student = new Student(60, "Artem", 3, null);
        restTemplate.postForObject("http://localhost:" + port + "/student/post", student, Student.class);
        ResponseEntity<Student> response = restTemplate.exchange(
                "http://localhost:" + port + "/student/" + student.getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(student),
                Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteFacultyTest() throws Exception {
        Faculty faculty = new Faculty(5L, "Test", "Test1", null);
        restTemplate.postForObject("http://localhost:" + port + "/faculty/post", faculty, Faculty.class);
        ResponseEntity<Faculty> response = restTemplate.exchange(
                "http://localhost:" + port + "/faculty/" + faculty.getId(),
                HttpMethod.DELETE,
                new HttpEntity<>(faculty),
                Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void filterStudentByAgeBetweenTest() throws Exception {
        final List<Student> students = List.of(
                new Student(60L, "Test1", 3, null),
                new Student(60L, "Test2", 14, null),
                new Student(60L, "Test3", 23, null),
                new Student(60L, "Test4", 33, null)
        );
        ResponseEntity<Collection<Student>> tested = restTemplate.exchange(
                "http://localhost:" + port + "/student/age?min=" + 10 + "&max=" + 30,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Collection<Student>>() {
                }
        );
        assertThat(tested.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(tested.getBody().size()).isEqualTo(students.size());
    }

    @Test
    void findFacultyByStudentID() throws Exception {
        Student student = new Student(60L, "Artem", 3, null);
        restTemplate.postForObject("http://localhost:" + port + "/student/post", student, Student.class);
        Faculty faculty = new Faculty(5L, "Test", "Test1", null);
        restTemplate.postForObject("http://localhost:" + port + "/faculty/post", faculty, Faculty.class);
        ResponseEntity<Faculty> tested = restTemplate.exchange("http://localhost:" + port + "/student/findByFaculty" + student.getId(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Faculty.class);
        assertThat(tested.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void findStudentByFaculty() throws Exception {
//        final List<Student> students = List.of(
        Student student = new Student(60L, "Test1", 3, new Faculty(1L, "test1", "test1.1", null));
        restTemplate.postForObject("http://localhost:" + port + "/student/post", student, Student.class);

        Student student1 = new Student(61L, "Test2", 14, new Faculty(5L, "Test", "Test1", null));
        restTemplate.postForObject("http://localhost:" + port + "/student/post", student1, Student.class);

        Student student2 = new Student(62L, "Test3", 23, new Faculty(5L, "Test", "Test1", null));
        restTemplate.postForObject("http://localhost:" + port + "/student/post", student2, Student.class);

        Student student3 = new Student(63L, "Test4", 33, new Faculty(1L, "test1", "test1.1", null));
        restTemplate.postForObject("http://localhost:" + port + "/student/post", student3, Student.class);

//        );

//        Student student = new Student(60L, "Artem", 3, );
//        restTemplate.postForObject("http://localhost:" + port + "/student/post", student, Student.class);
        Faculty faculty = new Faculty(5L, "Test", "Test1", null);
        restTemplate.postForObject("http://localhost:" + port + "/faculty/post", faculty, Faculty.class);
        ResponseEntity<Collection<Student>> tested = restTemplate.exchange("http://localhost:" + port + "/faculty/findByStudent/"
                        + faculty.getId(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Collection<Student>>() {
                }
        );
        assertThat(tested.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
