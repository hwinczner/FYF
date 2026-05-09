package com.hw.fyf.models;

import com.hw.fyf.enums.PlanStatus;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//{
//        "id": "uuid",
//        "title": "Become a Software Engineer",
//        "status": "IN_PROGRESS",
//        "targetRole": {
//        "id": "uuid",
//        "title": "Software Engineer"
//        },
//        "targetDate": "2028-01-15",
//        "milestoneCount": 8,
//        "milestonesCompleted": 3,
//        "overallPercentage": 35
//        }

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userplans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    private LocalDate targetDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "target_role_id")
    private Role targetRole;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Milestone> milestones = new ArrayList<>();

}
