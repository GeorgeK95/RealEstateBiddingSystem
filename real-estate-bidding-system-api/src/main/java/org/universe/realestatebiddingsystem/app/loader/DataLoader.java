package org.universe.realestatebiddingsystem.app.loader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.universe.realestatebiddingsystem.user.model.entity.Role;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;
import org.universe.realestatebiddingsystem.user.repository.RoleRepository;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

import java.util.HashSet;

@Component
public class DataLoader implements ApplicationRunner {

    private static final String ADMIN_EMAIL = "admin@abv.bg";
    private static final String ADMIN = "admin";
    private static final String ADMIN_PASS = "adminadmin";

    private static final String USER_EMAIL = "user@abv.bg";
    private static final String USER = "user";
    private static final String USER_PASS = "useruser";

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.seedRoles();
    }

    private void seedRoles() {
        Role roleUser = new Role();
        roleUser.setName(RoleName.ROLE_USER);
        Role roleAdmin = new Role();
        roleAdmin.setName(RoleName.ROLE_ADMIN);

        this.roleRepository.save(roleUser);
        this.roleRepository.save(roleAdmin);
    }

    private void seedUsers() {
        User admin = new User(
                "jeki_7@abv.bg",
                "jeki_7@abv.bg",
                "Georgi",
                "Kopchev",
                "0885705258",
                "Plovdiv",
                new HashSet<>(this.roleRepository.findAll()),
                false
        );

        this.userRepository.save(admin);

        /*User user = new User(USER, USER_EMAIL, USER_PASS);
        user.setRoles(Set.of(roleUser));
        this.userRepository.save(user);

        Town initialTown = new Town(MY_FIRST_TOWN);
        initialTown.setOwner(user);
        this.townRepository.save(initialTown);

        User mrusnik = new User("mrusnik", "mrusnik@abv.bg", "mrusnik");
        mrusnik.setRoles(Set.of(roleUser));
        this.userRepository.save(mrusnik);

        Town initialTownMrusnik = new Town(MY_FIRST_TOWN);
        initialTownMrusnik.setOwner(mrusnik);
        this.townRepository.save(initialTownMrusnik);

        User user2 = new User("user2", "user2@abv.bg", "user2");
        user2.setRoles(Set.of(roleUser));
        this.userRepository.save(user2);

        User user3 = new User("user3", "user3@abv.bg", "user3");
        user3.setRoles(Set.of(roleUser));
        this.userRepository.save(user3);

        User user4 = new User("user4", "user4@abv.bg", "user4");
        user4.setRoles(Set.of(roleUser));
        this.userRepository.save(user4);

        User user5 = new User("user5", "user5@abv.bg", "user5");
        user5.setRoles(Set.of(roleUser));
        this.userRepository.save(user5);

        User user6 = new User("user6", "user6@abv.bg", "user6");
        user6.setRoles(Set.of(roleUser));
        this.userRepository.save(user6);

        User user7 = new User("user7", "user7@abv.bg", "user7");
        user7.setRoles(Set.of(roleUser));
        this.userRepository.save(user7);

        User user8 = new User("user8", "user8@abv.bg", "user8");
        user8.setRoles(Set.of(roleUser));
        this.userRepository.save(user8);

        User user9 = new User("user9", "user9@abv.bg", "user9");
        user9.setRoles(Set.of(roleUser));
        this.userRepository.save(user9);

        User user10 = new User("user10", "user10@abv.bg", "user10");
        user10.setRoles(Set.of(roleUser));
        this.userRepository.save(user10);*/
    }

}
