package org.universe.realestatebiddingsystem.app.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

import java.util.Optional;

import static org.universe.realestatebiddingsystem.app.util.AppConstants.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) throw new UsernameNotFoundException(USER_NOT_FOUND_WITH_EMAIL_MESSAGE + email);

        if (user.getIsBanned()) throw new UsernameNotFoundException(BANNED_USER_MESSAGE);

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) throw new UsernameNotFoundException(USER_NOT_FOUND_WITH_ID_MESSAGE + id);

        return UserPrincipal.create(user.get());
    }

}