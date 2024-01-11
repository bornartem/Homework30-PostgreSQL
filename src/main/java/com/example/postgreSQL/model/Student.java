package com.example.postgreSQL.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Objects;
@Entity
@Data
public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int age;
}
