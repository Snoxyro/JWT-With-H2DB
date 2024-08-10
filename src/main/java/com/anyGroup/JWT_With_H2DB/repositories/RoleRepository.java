package com.anyGroup.JWT_With_H2DB.repositories;

import com.anyGroup.JWT_With_H2DB.entities.RoleEntity;
import com.anyGroup.JWT_With_H2DB.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(RoleEnum name);

}
