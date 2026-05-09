package com.hw.fyf.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class RoleResponseDTO {

    private UUID id;
    private String title;

}
