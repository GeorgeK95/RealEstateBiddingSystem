package org.universe.realestatebiddingsystem.app.loader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.universe.realestatebiddingsystem.user.model.entity.Role;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;
import org.universe.realestatebiddingsystem.user.repository.RoleRepository;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private static final String DBNAME = "rebs_db";
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin9808";


    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.seedUsersAndRoles();
        this.seedCities();
    }

    private void seedCities() {
        /*String useQuery = "USE " + DBNAME + "; ";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                statement.execute(useQuery);
                String addVillainQuery = "insert into villains(name, evilness_factor)\n" +
                        "values ('" + villainName + "', 'evil')";
                statement.execute(addVillainQuery);
            }
        } catch (SQLException e) {
            connection.rollback();
            System.out.println("Fail");
        }*/
    }

    private void seedUsersAndRoles() {
        Role roleAdmin = new Role();
        roleAdmin.setName(RoleName.ROLE_ADMIN);
        this.roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setName(RoleName.ROLE_USER);
        this.roleRepository.save(roleUser);

        User admin = new User(
                "drogba@abv.bg",
                "drogba",
                "Didier",
                "Drogba",
                "1111111111",
                "Kotdivuar",
                new HashSet<>(Set.of(roleAdmin, roleUser)),
                false
        );

        this.userRepository.save(admin);

        User lampard = new User(
                "lampard@abv.bg",
                "lampard",
                "Frank",
                "Lampard",
                "8888888888",
                "England",
                new HashSet<>(Set.of(roleUser)),
                false
        );
        lampard.setRoles(Set.of(roleUser));
        this.userRepository.save(lampard);

        User terry = new User(
                "terry@abv.bg",
                "terryterry",
                "john",
                "terry",
                "2626262626",
                "England",
                new HashSet<>(Set.of(roleUser)),
                false
        );
        lampard.setRoles(Set.of(roleUser));
        this.userRepository.save(terry);
    }

}
