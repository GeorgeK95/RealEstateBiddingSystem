package org.universe.realestatebiddingsystem.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.universe.realestatebiddingsystem.user.model.entity.User;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.ID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Boolean existsByEmail(String email);

    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.isBanned = true where u.id = :id")
    boolean banUser(@Param(ID) Long id);
}
