package com.hw.fyf.dtos;

import com.hw.fyf.enums.PlanStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PlanSummaryDTO {

    private UUID id;
    private String title;
    private PlanStatus status;
    private LocalDate targetDate;

    // nested object — just id and title from the Role entity
    private RoleSummaryDTO targetRole;

    // these three are calculated, not stored in the db
    private int milestoneCount;
    private int milestonesCompleted;
    private int overallPercentage;


}
