package ua.sms4f.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.sms4f.entity.Role;
import ua.sms4f.entity.UserDB;
import ua.sms4f.repository.UserDBRepository;

import java.util.Set;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserDBRepository repository) {

        return args -> {
            String name = "adam";
            if (repository.findByLogin(name) == null) {
                log.info("Preloading " + repository.save(new UserDB(name, "123", "second user", Set.of(Role.USER))));
            } else if (!repository.findByLogin(name).getLogin().equals(name)) {
                log.info("Preloading " + repository.save(new UserDB(name, "123", "second user", Set.of(Role.USER))));
            }

            name = "eva";
            if (repository.findByLogin(name) == null) {
                log.info("Preloading " + repository.save(new UserDB(name, "123", "second user", Set.of(Role.USER))));
            } else if (!repository.findByLogin(name).getLogin().equals(name)) {
                log.info("Preloading " + repository.save(new UserDB(name, "123", "second user", Set.of(Role.USER))));
            }

            name = "1";
            if (repository.findByLogin(name) == null) {
                log.info("Preloading " + repository.save(new UserDB(name, "1", "admin user", Set.of(Role.USER, Role.ADMIN))));
            } else if (!repository.findByLogin(name).getLogin().equals(name)) {
                log.info("Preloading " + repository.save(new UserDB(name, "1", "admin user", Set.of(Role.USER, Role.ADMIN))));
            }


            name = "admin";
            if (repository.findByLogin(name) == null) {
                log.info("Preloading " + repository.save(new UserDB(name, "123", "admin user", Set.of(Role.USER, Role.ADMIN))));
            } else if (!repository.findByLogin(name).getLogin().equals(name)) {
                log.info("Preloading " + repository.save(new UserDB(name, "123", "admin user", Set.of(Role.USER, Role.ADMIN))));
            }

            name = "root";
            if (repository.findByLogin(name) == null) {
                log.info("Preloading " + repository.save(new UserDB(name, "123", "super user", Set.of(Role.SUPERADMIN))));
            } else if (!repository.findByLogin(name).getLogin().equals(name)) {
                log.info("Preloading " + repository.save(new UserDB(name, "123", "super user", Set.of(Role.SUPERADMIN))));
            }


        };
    }
}