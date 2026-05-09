package com.hw.fyf.services;

import com.hw.fyf.dtos.RoleSummaryDTO;
import com.hw.fyf.exceptions.MissingException;
import com.hw.fyf.models.Role;
import com.hw.fyf.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

//    public RoleSummaryDTO getRoleById(UUID id){
//        Role role = roleRepository.findById(id).
//                orElseThrow(() -> new ResourceNotFoundException("Cannot find role:" + id));
//
//
//    }
//
//    private RoleSummaryDTO mapToSummaryDTO(Role role){
//        RoleSummaryDTO dto = new RoleSummaryDTO();
//    }
}
