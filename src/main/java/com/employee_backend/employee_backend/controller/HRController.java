package com.employee_backend.employee_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_backend.employee_backend.model.Employee;
import com.employee_backend.employee_backend.model.Experience;
import com.employee_backend.employee_backend.model.Position;
import com.employee_backend.employee_backend.model.Skill;
import com.employee_backend.employee_backend.model.Wing;
import com.employee_backend.employee_backend.repository.EmployeeRepository;
import com.employee_backend.employee_backend.repository.PositionRepository;
import com.employee_backend.employee_backend.repository.SkillRepository;
import com.employee_backend.employee_backend.repository.WingRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hr")
@CrossOrigin(origins = "http://localhost:3000")
public class HRController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private SkillRepository skillRepo;

    @Autowired
    private WingRepository wingRepo;

    @Autowired
    private PositionRepository positionRepo;

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {

        Wing wing = wingRepo.findById(employee.getWing().getId())
                .orElseThrow(() -> new RuntimeException("Wing not found"));
        Position dept = positionRepo.findById(employee.getPosition().getId())
                .orElseThrow(() -> new RuntimeException("Position not found"));
        employee.setWing(wing);
        employee.setPosition(dept);

        List<Skill> fullSkills = employee.getSkills().stream()
                .map(s -> skillRepo.findById(s.getId())
                        .orElseThrow(() -> new RuntimeException("Skill not found: " + s.getId())))
                .toList();

        if (employee.getExperiences() != null) {
            for (Experience exp : employee.getExperiences()) {
                exp.setEmployee(employee);
            }
        }
        employee.setTotalExperience(employee.getTotalExperience());

        employee.setSkills(fullSkills);
        Employee savedEmployee = employeeRepo.save(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@Valid @RequestBody Employee employeeDetails) {
        Employee existingEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));

        // Update basic fields
        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setGender(employeeDetails.getGender());
        existingEmployee.setDateOfBirth(employeeDetails.getDateOfBirth());
        existingEmployee.setAge(employeeDetails.getAge());
        existingEmployee.setDateOfJoined(employeeDetails.getDateOfJoined());
        existingEmployee.setAddress(employeeDetails.getAddress());
        existingEmployee.setHasExperience(employeeDetails.getHasExperience());
        existingEmployee.setTotalExperience(employeeDetails.getTotalExperience());
        existingEmployee.setPhoto(employeeDetails.getPhoto());

        // Update Wing
        Wing wing = wingRepo.findById(employeeDetails.getWing().getId())
                .orElseThrow(() -> new RuntimeException("Wing not found"));
        existingEmployee.setWing(wing);

        // Update Position
        Position dept = positionRepo.findById(employeeDetails.getPosition().getId())
                .orElseThrow(() -> new RuntimeException("Position not found"));
        existingEmployee.setPosition(dept);

        // Clear existing experiences and set new ones
        existingEmployee.getExperiences().clear(); // This should work if experiences is mutable
        if (employeeDetails.getExperiences() != null) {
            for (Experience exp : employeeDetails.getExperiences()) {
                exp.setEmployee(existingEmployee);
                existingEmployee.getExperiences().add(exp);
            }
        }

        Employee updatedEmployee = employeeRepo.save(existingEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

}
