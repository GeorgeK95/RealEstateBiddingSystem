package org.universe.realestatebiddingsystem.user.service.api;

import org.universe.realestatebiddingsystem.user.model.entity.Role;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName roleUser);
}
