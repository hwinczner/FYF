package com.hw.fyf.controllers;

import com.hw.fyf.models.Skill;
import com.hw.fyf.services.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("skill")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<List<Skill>> findAllSkills() {
        return ResponseEntity.ok(skillService.findAllSkills());
    }

}
