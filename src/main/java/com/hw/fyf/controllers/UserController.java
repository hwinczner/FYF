package com.hw.fyf.controllers;

import com.hw.fyf.exceptions.ConflictException;
import com.hw.fyf.models.User;
import com.hw.fyf.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) throws ConflictException {
        userService.validateUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findById(@PathVariable UUID userId){
        return ResponseEntity.ok(userService.findById(userId));
    }
}
