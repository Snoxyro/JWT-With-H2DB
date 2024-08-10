package com.anyGroup.JWT_With_H2DB.repositories;

import com.anyGroup.JWT_With_H2DB.entities.RoleEntity;
import com.anyGroup.JWT_With_H2DB.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleEnum name);

}
