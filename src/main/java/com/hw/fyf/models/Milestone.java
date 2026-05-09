package com.hw.fyf.models;

import com.hw.fyf.enums.MilestoneStatus;
import com.hw.fyf.enums.PlanStatus;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

//[
//        {
//        "id": "uuid",
//        "title": "Learn Java",
//        "description": "Complete Java fundamentals course",
//        "status": "COMPLETED",
//        "dueDate": "2026-03-01",
//        "completedAt": "2026-02-28",
//        "order": 1
//        },
//        {
//        "id": "uuid",
//        "title": "Build first Spring Boot project",
//        "description": "Build a REST API with at least 5 endpoints",
//        "status": "IN_PROGRESS",
//        "dueDate": "2026-06-01",
//        "completedAt": null,
//        "order": 2
//        }
//        ]

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "milestones")
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;  // was uuid

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private MilestoneStatus status;

    private LocalDate dueDate;
    private LocalDateTime completedAt;

    @Column(name = "order_index")  // was order
    private int orderIndex;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
}
