package org.universe.realestatebiddingsystem.user.model.entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;

import javax.persistence.*;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.ROLES;
import static org.universe.realestatebiddingsystem.app.util.AppConstants.ROLE_NAME_LENGH_VALUE;

@Entity
@Table(name = ROLES)
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = ROLE_NAME_LENGH_VALUE, nullable = false)
    private RoleName name;

    @Override
    public String getAuthority() {
        return this.name.name();
    }
}
