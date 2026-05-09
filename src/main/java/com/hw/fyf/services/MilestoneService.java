package com.hw.fyf.services;

import com.hw.fyf.models.Milestone;
import com.hw.fyf.repo.MilestoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    public List<Milestone> findAllMilestones(){
        return milestoneRepository.findAll();
    }
}
