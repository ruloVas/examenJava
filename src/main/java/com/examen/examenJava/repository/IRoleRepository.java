package com.examen.examenJava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.examenJava.model.entity.RoleEntity;
import com.examen.examenJava.model.enums.RoleNameEnum;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    
	Optional<RoleEntity> findByName(RoleNameEnum roleName);

}
