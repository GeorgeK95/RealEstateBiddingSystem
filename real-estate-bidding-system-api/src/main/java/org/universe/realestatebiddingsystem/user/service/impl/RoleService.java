package org.universe.realestatebiddingsystem.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.universe.realestatebiddingsystem.user.model.entity.Role;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;
import org.universe.realestatebiddingsystem.user.repository.RoleRepository;
import org.universe.realestatebiddingsystem.user.service.api.IRoleService;

import java.util.Optional;

@Service
@Transactional
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(RoleName roleUser) {
        return this.roleRepository.findByName(RoleName.ROLE_USER);
    }
}
