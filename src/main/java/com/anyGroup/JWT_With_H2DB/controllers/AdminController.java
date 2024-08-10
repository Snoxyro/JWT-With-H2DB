package com.anyGroup.JWT_With_H2DB.controllers;

import com.anyGroup.JWT_With_H2DB.dto.RegisterUserDto;
import com.anyGroup.JWT_With_H2DB.entities.UserEntity;
import com.anyGroup.JWT_With_H2DB.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<UserEntity> createAdministrator(@RequestBody RegisterUserDto registerUserDto) {
        UserEntity createdAdmin = userService.createAdministrator(registerUserDto);

        return ResponseEntity.ok(createdAdmin);
    }
}
