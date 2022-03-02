package com.DPC.spring.repositories;

import com.DPC.spring.entities.ERole;
import com.DPC.spring.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // find Role by name
    Optional<Role> findByName(ERole name);
}
