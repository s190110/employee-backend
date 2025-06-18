package com.employee_backend.employee_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_backend.employee_backend.model.Department;
import com.employee_backend.employee_backend.model.Employee;
import com.employee_backend.employee_backend.model.User;



public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(Department department);
    Optional<Employee> findByUser(User user);
}
