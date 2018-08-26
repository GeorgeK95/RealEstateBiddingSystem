package org.universe.realestatebiddingsystem.app.loader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.universe.realestatebiddingsystem.estates.estate.model.entity.Estate;
import org.universe.realestatebiddingsystem.estates.estate.repository.EstateRepository;
import org.universe.realestatebiddingsystem.estates.image.model.entity.Image;
import org.universe.realestatebiddingsystem.estates.image.repository.ImageRepository;
import org.universe.realestatebiddingsystem.estates.peculiarity.model.entity.Peculiarity;
import org.universe.realestatebiddingsystem.estates.peculiarity.repository.PeculiarityRepository;
import org.universe.realestatebiddingsystem.user.model.entity.Role;
import org.universe.realestatebiddingsystem.user.model.entity.User;
import org.universe.realestatebiddingsystem.user.model.enumeration.RoleName;
import org.universe.realestatebiddingsystem.user.repository.RoleRepository;
import org.universe.realestatebiddingsystem.user.repository.UserRepository;

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
    private final PeculiarityRepository peculiarityRepo;
    private final EstateRepository estateRepo;
    private final ImageRepository imageRepo;


    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PeculiarityRepository peculiarityRepo, EstateRepository estateRepo, ImageRepository imageRepo) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.peculiarityRepo = peculiarityRepo;
        this.estateRepo = estateRepo;
        this.imageRepo = imageRepo;
    }

    @Override
    public void run(ApplicationArguments args) {
        this.seedUsersAndRoles();
        this.seedEstates();
//        this.seedCities();
    }

    private void seedEstates() {
        this.persistFirstEstate();
        this.persistSecondEstate();
        this.persistThirdEstate();
        this.persistFourthEstate();
        this.persistFifthEstate();
        this.persistSixthEstate();
        this.persistSeventhEstate();
        this.persistPeculiarities();
    }

    private void persistPeculiarities() {
        Set<Peculiarity> p = Set.of(
                new Peculiarity("Panel"),
                new Peculiarity("Brick"),
                new Peculiarity("LFS"),
                new Peculiarity("CFW"),
                new Peculiarity("Pool"),
                new Peculiarity("Air Condition"),
                new Peculiarity("Garage"),
                new Peculiarity("In Build"),
                new Peculiarity("Trimmer Joints"),
                new Peculiarity("Elevator")
        );

        this.peculiarityRepo.saveAll(p);
    }

    private void persistSeventhEstate() {
        Estate e = new Estate();
        e.setAction("sell");
        e.setType("Place");
        e.setCity("София");
        e.setArea(300D);
        e.setCoverImage(new Image("image", "https://i2.homes.bg/2018-08-26_1/28299403b.jpg"));
        e.setAdditionalInfo("Продавам парцел с площ 300 кв.м. в гр. София, кв. Гео Милев, ул. Николай Коперник, в непосредствена близост до х-л Плиска. Парцелът попада в устройствена зона за високоетажно застрояване(Жг). Плътност на застрояване - 60%; Кинт - 3.5; Кота корниз - 26 м.; ЗАБЕЛЕЖКА: НОВИЯТ СОБСТВЕНИК СЕ АНГАЖИРА С ИЗВАЖДАНЕТО НА НОВ ПУП");
        e.setPrice(1D);
        e.setAuthor(this.userRepository.findByEmail("terry@abv.bg"));

        this.estateRepo.save(e);
    }

    private void persistSixthEstate() {
        Estate e = new Estate();
        e.setAction("sell");
        e.setType("Place");
        e.setCity("София");
        e.setArea(6410D);
        e.setCoverImage(new Image("image", "https://i3.homes.bg/2018-08-26_1/28299372b.jpg"));
        e.setAdditionalInfo("Продава се УПИ 6410 кв. м. в гр. София, Иллиенци, Военна рампа. Разположен в смесена производствена зона с лице на асфалтиран път. Показатели на застрояване: с показатели пл. 55%, кинт. 1, 5, озел. 30 % РЗП: 615 кв. м. , подходящ за производствени цели и складова дейност. За повече информация тел. 0877972227");
        e.setPrice(75D);
        e.setAuthor(this.userRepository.findByEmail("terry@abv.bg"));

        this.estateRepo.save(e);
    }

    private void persistFifthEstate() {
        Estate e = new Estate();
        e.setAction("sell");
        e.setType("House");
        e.setCity("София");
        e.setArea(300D);
        e.setCoverImage(new Image("image", "https://i1.homes.bg/2018-08-25_1/28222605b.jpg"));
        List<Image> images = List.of(
                new Image("image", "https://i3.homes.bg/2018-08-25_1/28222606b.jpg"),
                new Image("image", "https://i2.homes.bg/2018-08-25_1/28222608o.jpg"),
                new Image("image", "https://i1.homes.bg/2018-08-25_1/28222613b.jpg")
        );
        e.setImages(images);
        Set<Peculiarity> allByName = this.peculiarityRepo.findAllByName(Set.of("Garage", "Brick", "In Build", "Elevator", "Pool"));
        e.setPeculiarities(allByName);
        e.setAdditionalInfo("Предлагам за продажба хубава масивна къща във в.з. Малинова долина, в близост до Академия на МВР. Районът е с много зеленина, застроен с целогодишно обитаеми къщи. Има бърза връзка с Околовръстен път и бул. Александър Малинов, както и с метростанция Бизнес парк София. Къщата е развита на три етажа, с РЗП от 300 кв. м.");
        e.setPrice(1667D);
        e.setAuthor(this.userRepository.findByEmail("terry@abv.bg"));

        this.estateRepo.save(e);

        images.forEach(i -> i.setEstate(e));
        this.imageRepo.saveAll(images);
    }

    private void persistFourthEstate() {
        Estate e = new Estate();
        e.setAction("sell");
        e.setType("Four+ Room Apartment");
        e.setCity("София");
        e.setArea(207D);
        e.setCoverImage(new Image("image", "https://i1.homes.bg/2018-08-17_2/27879607b.jpg"));
        List<Image> images = List.of(
                new Image("image", "https://i4.homes.bg/2018-08-17_2/27879608b.jpg"),
                new Image("image", "https://i4.homes.bg/2018-08-17_2/27879609b.jpg"),
                new Image("image", "https://i4.homes.bg/2018-08-25_2/28260679b.jpg")
        );
        e.setImages(images);
        Set<Peculiarity> allByName = this.peculiarityRepo.findAllByName(Set.of("Garage", "Brick", "In Build", "Elevator", "Pool"));
        e.setPeculiarities(allByName);
        e.setAdditionalInfo("Без комисионна от купувача! Гарантирана добра инвестиция!Без такса огледи! Огледи по всяко време! Чудесен панорамен апартамент с два подземни гаража, включени в цената на апартамента в луксозна жилищна сграда!Намира се на 1, 2 км. от центъра на столицата! Представлява: три спални, две бани, просторна всекидневна с трапезария и кухненска част, две тераси!");
        e.setPrice(686D);
        e.setAuthor(this.userRepository.findByEmail("drogba@abv.bg"));

        this.estateRepo.save(e);

        images.forEach(i -> i.setEstate(e));
        this.imageRepo.saveAll(images);
    }

    private void persistThirdEstate() {
        Estate e = new Estate();
        e.setAction("rent");
        e.setType("Garage");
        e.setCity("София");
        e.setArea(16D);
        e.setCoverImage(new Image("image", "https://i1.homes.bg/2018-08-17_2/27879607b.jpg"));
        List<Image> images = List.of(
                new Image("image", "https://i4.homes.bg/2018-08-17_2/27879608b.jpg"),
                new Image("image", "https://i4.homes.bg/2018-08-17_2/27879609b.jpg"),
                new Image("image", "https://i4.homes.bg/2018-08-25_2/28260679b.jpg")
        );
        e.setImages(images);
        Set<Peculiarity> allByName = this.peculiarityRepo.findAllByName(Set.of("Garage"));
        e.setPeculiarities(allByName);
        e.setAdditionalInfo("Алианс имоти отдава под наем подземен гараж в нова сграда на бул.Сахаров, в близост до метростанцията и пазара. Свободен ! Офертата е за ч.л За контакт: 0894636237.");
        e.setPrice(100D);
        e.setAuthor(this.userRepository.findByEmail("terry@abv.bg"));

        this.estateRepo.save(e);

        images.forEach(i -> i.setEstate(e));
        this.imageRepo.saveAll(images);
    }

    private void persistSecondEstate() {
        Estate e = new Estate();
        e.setAction("sale");
        e.setType("Two Room Apartment");
        e.setCity("София");
        e.setArea(99D);
        e.setCoverImage(new Image("image", "https://i2.homes.bg/2018-08-25_2/28260676b.jpg"));
        List<Image> images = List.of(
                new Image("image", "https://i3.homes.bg/2018-08-25_2/28260677b.jpg"),
                new Image("image", "https://i2.homes.bg/2018-08-25_2/28260678b.jpg"),
                new Image("image", "https://i4.homes.bg/2018-08-25_2/28260679b.jpg")
        );
        e.setImages(images);
        Set<Peculiarity> allByName = this.peculiarityRepo.findAllByName(Set.of("Panel", "Elevator", "In Build"));
        e.setPeculiarities(allByName);
        e.setAdditionalInfo(
                "РАЗПРЕДЕЛЕНИЕ: входно антре, хол с кухненски бокс, 2 спални, баня с тоалетна, тераса; ЛОКАЦИЯ: В тих и спокоен район, в близост до училище и детска градина.Можем да организираме абсолютно безплатен оглед в удобно за Вас време. Агенцията посредничи от първият оглед до окончателно изповядване на сделката, също така осигуряваме пълно юридическо обслужване.");
        e.setPrice(984D);
        e.setAuthor(this.userRepository.findByEmail("lampard@abv.bg"));

        this.estateRepo.save(e);

        images.forEach(i -> i.setEstate(e));
        this.imageRepo.saveAll(images);
    }

    private void persistFirstEstate() {
        Estate e = new Estate();
        e.setAction("sale");
        e.setType("Three Room Apartment");
        e.setCity("Пловдив");
        e.setArea(99D);
        e.setCoverImage(new Image("image", "https://i3.homes.bg/2018-04-05_2/22449203b.jpg"));
        List<Image> images = List.of(
                new Image("image", "https://i3.homes.bg/2018-04-05_2/22449203b.jpg"),
                new Image("image", "https://i3.homes.bg/2018-04-05_2/22449203b.jpg"),
                new Image("image", "https://i1.homes.bg/2018-07-05_2/25854880b.jpg")
        );
        e.setImages(images);
        Set<Peculiarity> allByName = this.peculiarityRepo.findAllByName(Set.of("Garage", "Elevator", "In Build"));
        e.setPeculiarities(allByName);
        e.setAdditionalInfo("Без комисион от купувача! Цени на инвеститор. През месец март 2018 започна строителството на първата сграда с два входа от общо три сгради в непосредствена близост до бул.\"Цар Борис III\", бул. \"Овча купел\" и паркове \"Славия\" и Овча купел. Сградата е ситуирана на тиха улица с бърз достъп до трамвайни и автобусни спирки. Изложението на апартаменти е много добро.");
        e.setPrice(854D);
        e.setAuthor(this.userRepository.findByEmail("lampard@abv.bg"));

        this.estateRepo.save(e);

        images.forEach(i -> i.setEstate(e));
        this.imageRepo.saveAll(images);
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
