package com.hw.fyf.controllers;

import com.hw.fyf.dtos.PlanSummaryDTO;
import com.hw.fyf.dtos.requests.CreatePlanRequestDTO;
import com.hw.fyf.models.Plan;
import com.hw.fyf.services.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("plan")
@RequiredArgsConstructor
public class PlansController {

    private final PlanService planService;

    @GetMapping
    public List<Plan> getPlans(){
        return planService.getPlans();
    }

    @PostMapping("/{userId}/plans")
    public ResponseEntity<PlanSummaryDTO> createPlan(
        @PathVariable UUID userId,
        @RequestBody CreatePlanRequestDTO request) {
            PlanSummaryDTO created = planService.createPlan(userId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
