package com.example.postgreSQL.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"students"})
public class Faculty {
    @Getter
    @Id
    @GeneratedValue
    private long id;
    @Getter
    private String name;
    @Getter
    private String color;

    @OneToMany(mappedBy = "faculty")
    @JsonManagedReference
    private Collection<Student> students;

//    public Faculty(long id, String name, String color) {
//        this.id = id;
//        this.name = name;
//        this.color = color;
//    }
//    public Faculty(){
//
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public Collection<Student> getStudents() {
//        return students;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Faculty faculty = (Faculty) o;
//        return id == faculty.id && Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color) && Objects.equals(students, faculty.students);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, color, students);
//    }
//
//    @Override
//    public String toString() {
//        return "Faculty{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", color='" + color + '\'' +
//                ", students=" + students +
//                '}';
//    }
}
