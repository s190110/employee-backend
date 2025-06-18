package com.employee_backend.employee_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToOne
    private Department department;

    @OneToOne(mappedBy = "employee")
    private User user;

}
