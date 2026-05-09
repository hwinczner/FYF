package com.hw.fyf.dtos.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

import java.util.UUID;

@Data
public class CreatePlanRequestDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Target role is required")
    private UUID targetRoleId;

    @NotNull(message = "Target date is required")
    @Future(message = "Target date must be in the future")
    private LocalDate targetDate;
}
