package pl.dlsd.profile.system.profile.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "pl.dlsd.profile.system.profile.service.dataaccess")
@EntityScan(basePackages = "pl.dlsd.profile.system.profile.service.dataaccess")
@SpringBootApplication(scanBasePackages = "pl.dlsd")
public class ProfileServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProfileServiceApplication.class, args);
    }
}
