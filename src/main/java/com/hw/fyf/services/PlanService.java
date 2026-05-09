package com.hw.fyf.services;

import com.hw.fyf.dtos.PlanSummaryDTO;
import com.hw.fyf.dtos.RoleSummaryDTO;
import com.hw.fyf.dtos.requests.CreatePlanRequestDTO;
import com.hw.fyf.dtos.responses.PlanResponseDTO;
import com.hw.fyf.dtos.responses.RoleResponseDTO;
import com.hw.fyf.enums.MilestoneStatus;
import com.hw.fyf.enums.PlanStatus;
import com.hw.fyf.exceptions.MissingException;
import com.hw.fyf.models.Plan;
import com.hw.fyf.models.Role;
import com.hw.fyf.models.User;
import com.hw.fyf.repo.PlanRepository;
import com.hw.fyf.repo.RoleRepository;
import com.hw.fyf.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<Plan> getPlans(){
        return planRepository.findAll();
    }

    public PlanSummaryDTO createPlan(UUID uuid, CreatePlanRequestDTO request) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + uuid));

        Role targetRole = roleRepository.findById(request.getTargetRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + request.getTargetRoleId()));

        Plan plan = Plan.builder()
                .title(request.getTitle())
                .status(PlanStatus.NOT_STARTED)
                .targetDate(request.getTargetDate())
                .targetRole(targetRole)
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .milestones(new ArrayList<>())
                .build();

        Plan saved = planRepository.save(plan);
        return mapToSummaryDTO(saved);
    }

    private PlanSummaryDTO mapToSummaryDTO(Plan plan) {
        int total = plan.getMilestones().size();
        int completed = (int) plan.getMilestones().stream()
                .filter(m -> m.getStatus() == MilestoneStatus.COMPLETED)
                .count();
        int percentage = total == 0 ? 0 : (completed * 100) / total;

        PlanSummaryDTO dto = new PlanSummaryDTO();
        dto.setId(plan.getId());
        dto.setTitle(plan.getTitle());
        dto.setStatus(plan.getStatus());
        dto.setTargetDate(plan.getTargetDate());
        dto.setTargetRole(new RoleSummaryDTO(
                plan.getTargetRole().getId(),
                plan.getTargetRole().getTitle()
        ));
        dto.setMilestoneCount(total);
        dto.setMilestonesCompleted(completed);
        dto.setOverallPercentage(percentage);

        return dto;
    }
}
