package com.employee_backend.employee_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_backend.employee_backend.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {}
