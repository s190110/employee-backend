package com.employee_backend.employee_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee_backend.employee_backend.model.Position;


public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByWingId(Long wingId);
    
    
}
