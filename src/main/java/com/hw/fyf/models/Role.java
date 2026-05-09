package com.hw.fyf.models;

import com.hw.fyf.enums.DemandLevel;
import com.hw.fyf.enums.EducationLevel;
import com.hw.fyf.enums.SeniorityLevel;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String industry;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer entrySalary;
    private Integer midSalary;
    private Integer seniorSalary;

    @Enumerated(EnumType.STRING)
    private DemandLevel demandLevel;

    @Enumerated(EnumType.STRING)
    private SeniorityLevel seniorityLevel;

    @Enumerated(EnumType.STRING)
    private EducationLevel requiredEducation;

    private Integer requiredExperienceYears;
    private Integer averageYearsToReach;

    @ManyToMany
    @JoinTable(
            name = "role_required_skills",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> requiredSkills = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "role_preferred_skills",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> preferredSkills = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "role_progression",
            joinColumns = @JoinColumn(name = "from_role_id"),
            inverseJoinColumns = @JoinColumn(name = "to_role_id")
    )
    private List<Role> nextRoles = new ArrayList<>();
}