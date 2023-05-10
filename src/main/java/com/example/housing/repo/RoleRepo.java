package com.example.housing.repo;

import com.example.housing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Optional<Role> findRoleByRoleName(String roleName);
}
