package com.employee_backend.employee_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee_backend.employee_backend.model.Skill;
import com.employee_backend.employee_backend.repository.SkillRepository;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {

    @Autowired
    private SkillRepository skillRepo;

    @GetMapping
    public List<Skill> getSkills() {
        return skillRepo.findAll();
    }

//    @GetMapping
//	public ResponseEntity<Map<String, Object>> getSkills() {
//		List<Skill> all = skillRepo.findAll();
//
//		Map<String, Object> response = new LinkedHashMap<>();
//		response.put("skills", all);
//		return ResponseEntity.ok(response);
//
//	}
    
    @PostMapping
    public Skill addSkill(@RequestBody Skill skill) {
        return skillRepo.save(skill);
    }
}
