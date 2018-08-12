package org.universe.realestatebiddingsystem.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.user.model.entity.Role;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
