package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class init {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public init(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initData() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("$2y$10$/U6KKct8hIyqVNr/WunfSeF80nj1r9eAPQCzenwtdTwnsQRNXJNmG"); //adminpass
        admin.setEmail("Oleg_Openkin@mail.com");
        admin.setSurname("AdminSurname");
        admin.setRoles(Collections.singleton(roleAdmin));
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword("$2y$10$4Ym0Nxq6OyzFyMDyX0ploOPrt4CxicXqBdRypQ4VY/QvDbo/LYdsW");  //userpass
        user.setEmail("Anisimov_Oleg@mail.com");
        user.setSurname("UserSurname");
        user.setRoles(Collections.singleton(roleUser));
        userRepository.save(user);
    }
}
