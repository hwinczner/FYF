package com.hw.fyf.dtos.responses;

import com.hw.fyf.enums.PlanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponseDTO {

    private UUID id;
    private String title;
    private PlanStatus status;
    private LocalDate targetDate;

    // nested object — just id and title from the Role entity
    private RoleResponseDTO targetRole;

    // these three are calculated, not stored in the db
    private int milestoneCount;
    private int milestonesCompleted;
    private int overallPercentage;

}