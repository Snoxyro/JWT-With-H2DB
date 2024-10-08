package com.anyGroup.JWT_With_H2DB.bootstrap;

import com.anyGroup.JWT_With_H2DB.dto.RegisterUserDto;
import com.anyGroup.JWT_With_H2DB.entities.RoleEntity;
import com.anyGroup.JWT_With_H2DB.entities.RoleEnum;
import com.anyGroup.JWT_With_H2DB.entities.UserEntity;
import com.anyGroup.JWT_With_H2DB.repositories.RoleRepository;
import com.anyGroup.JWT_With_H2DB.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminSeeder(RoleRepository roleRepository, UserRepository  userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent contextRefreshedEvent) { this.createSuperAdministrator(); }

    private void createSuperAdministrator() {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setUsername("Super Admin");
        userDto.setEmail("super.admin@email.com");
        userDto.setPassword("1234");

        Optional<RoleEntity> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<UserEntity> optionalUser = userRepository.findByUsername(userDto.getUsername());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }
}
