package com.demure.demure_auth.repository;

import com.demure.demure_auth.entity.Role;
import com.demure.demure_auth.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleType(RoleType roleType);
}
