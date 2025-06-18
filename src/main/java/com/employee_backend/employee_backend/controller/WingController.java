package com.employee_backend.employee_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_backend.employee_backend.model.Position;
import com.employee_backend.employee_backend.model.Wing;
import com.employee_backend.employee_backend.repository.PositionRepository;
import com.employee_backend.employee_backend.repository.WingRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class WingController {

    @Autowired
    private WingRepository wingRepo;

    @Autowired
    private PositionRepository positionRepo;

    @GetMapping("/wings")
    public List<Wing> getWings() {
        return wingRepo.findAll();
    }

    @GetMapping("/wings/{wingId}/positions")
    public List<Position> getDepartments(@PathVariable Long wingId) {
        return positionRepo.findByWingId(wingId);
    }
}
