package com.employee_backend.employee_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_backend.employee_backend.model.Employee;
import com.employee_backend.employee_backend.model.User;
import com.employee_backend.employee_backend.repository.EmployeeRepository;
import com.employee_backend.employee_backend.repository.UserRepository;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    @Autowired private EmployeeRepository employeeRepo;
    @Autowired private UserRepository userRepo;

    @GetMapping("/me")
    public Employee getMyInfo(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return employeeRepo.findByUser(user).orElse(null);
    }
}
