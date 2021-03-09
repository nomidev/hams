package com.huneth.hams.member.repository;

import com.huneth.hams.member.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
