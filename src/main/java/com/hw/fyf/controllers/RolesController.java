package com.hw.fyf.controllers;

import com.hw.fyf.dtos.RoleSummaryDTO;
import com.hw.fyf.models.Role;
import com.hw.fyf.repo.RoleRepository;
import com.hw.fyf.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("role")
public class RolesController {

    private final RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Role> getRoleById(@PathVariable UUID id){
//        RoleSummaryDTO roleSummaryDTO = roleService
//    }
}
