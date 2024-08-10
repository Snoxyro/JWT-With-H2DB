package com.anyGroup.JWT_With_H2DB.bootstrap;

import com.anyGroup.JWT_With_H2DB.entities.RoleEntity;
import com.anyGroup.JWT_With_H2DB.entities.RoleEnum;
import com.anyGroup.JWT_With_H2DB.repositories.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;


    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.ADMIN, "Administrator role",
                RoleEnum.SUPER_ADMIN, "Super Administrator role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<RoleEntity> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                RoleEntity roleToCreate = new RoleEntity();

                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleToCreate);
            });
        });
    }
}
