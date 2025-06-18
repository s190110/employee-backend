package com.employee_backend.employee_backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_backend.employee_backend.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
