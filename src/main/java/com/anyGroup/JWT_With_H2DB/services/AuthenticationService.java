package com.anyGroup.JWT_With_H2DB.services;

import com.anyGroup.JWT_With_H2DB.dto.LoginUserDto;
import com.anyGroup.JWT_With_H2DB.dto.RegisterUserDto;
import com.anyGroup.JWT_With_H2DB.entities.RoleEntity;
import com.anyGroup.JWT_With_H2DB.entities.RoleEnum;
import com.anyGroup.JWT_With_H2DB.entities.UserEntity;
import com.anyGroup.JWT_With_H2DB.repositories.RoleRepository;
import com.anyGroup.JWT_With_H2DB.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            RoleRepository roleRepository,
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity signup(RegisterUserDto input) {
        Optional<RoleEntity> optionalRole = this.roleRepository.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        UserEntity user = new UserEntity();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(this.passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return this.userRepository.save(user);
    }

    public UserEntity authenticate(LoginUserDto input) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return this.userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
