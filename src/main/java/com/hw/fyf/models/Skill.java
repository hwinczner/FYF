package com.hw.fyf.models;

import com.hw.fyf.enums.SkillLevel;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SkillLevel level; // BEGINNER, INTERMEDIATE, ADVANCED

    private String category; // e.g. "Programming", "Soft Skills", "Tools"
}