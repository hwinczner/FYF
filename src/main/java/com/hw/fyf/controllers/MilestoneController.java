package com.hw.fyf.controllers;

import com.hw.fyf.models.Milestone;
import com.hw.fyf.services.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("milestone")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping
    public List<Milestone> findAllMilestones(){
        return milestoneService.findAllMilestones();
    }
}
