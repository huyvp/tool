package com.tool.config;

import com.tool.entity.Role;
import com.tool.entity.User;
import com.tool.repo.RoleRepo;
import com.tool.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;

@Configuration
@Slf4j
public class InitialConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring",
            value = "datasource.driver-class-name",
            havingValue = "org.postgresql.Driver"
    )
    ApplicationRunner applicationRunner(UserRepo userRepo, RoleRepo roleRepo) {
        log.info("Initializing application start .......");
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                Role adminRole = roleRepo.save(Role.builder().name("ADMIN").build());
                roleRepo.save(Role.builder().name("USER").build());

                HashSet<Role> roles = new HashSet<>();
                roles.add(adminRole);

                User user = User.builder()
                        .knoxId("nv.huy1")
                        .ip("107.120.121.97")
                        .username("admin")
                        .password(passwordEncoder().encode("admin_admin"))
                        .roles(roles)
                        .build();
                userRepo.save(user);

                log.warn("Admin user have been created with default password");
            }
            log.info("Initializing application complete");
        };
    }
}
