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
@RequestMapping("/head")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentHeadController {
    @Autowired private EmployeeRepository employeeRepo;
    @Autowired private UserRepository userRepo;

    @GetMapping("/employees")
    public List<Employee> getDeptEmployees(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return employeeRepo.findByDepartment(user.getDepartment());
    }
}
