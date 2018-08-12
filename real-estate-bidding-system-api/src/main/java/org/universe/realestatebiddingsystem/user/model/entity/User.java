package org.universe.realestatebiddingsystem.user.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Entity
@Table(name = USERS)
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = EMAIL_MIN_VALUE, max = EMAIL_MAX_VALUE)
    @Email
    @Column(unique = true, nullable = false, length = EMAIL_MAX_VALUE)
    private String email;

    @NotBlank
//    @Size(min = PASSWORD_MIN_VALUE, max = PASSWORD_MAX_VALUE)
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false, length = USER_LAST_NAME_MAX_VALUE)
    @Size(min = USER_FIRST_NAME_MIN_VALUE, max = USER_FIRST_NAME_MAX_VALUE)
    private String firstName;

    @NotBlank
    @Size(min = USER_LAST_NAME_MIN_VALUE, max = USER_LAST_NAME_MAX_VALUE)
    @Column(nullable = false, length = USER_LAST_NAME_MAX_VALUE)
    private String lastName;

    @NotBlank
    @Pattern(regexp = TELEPHONE_REGEXP)
    @Column(nullable = false, length = TELEPHONE_LENGHT)
    private String telephone;

    //    @NotBlank
    @Size(min = TOWN_MIN_VALUE, max = TOWN_MAX_VALUE)
    @Column(length = TOWN_MAX_VALUE)
    private String town;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = USER_ROLES,
            joinColumns = @JoinColumn(name = USER_ID),
            inverseJoinColumns = @JoinColumn(name = ROLE_ID))
    private Set<Role> roles = new HashSet<>();

    @Column(nullable = false)
    private Boolean isBanned = false;

    @PrePersist
    public void onPrePersist() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.isBanned;
    }
}