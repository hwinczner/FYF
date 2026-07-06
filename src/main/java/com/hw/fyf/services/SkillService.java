package com.hw.fyf.services;

import com.hw.fyf.models.Skill;
import com.hw.fyf.repo.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public List<Skill> findAllSkills(){
        return skillRepository.findAll();
    }
}
