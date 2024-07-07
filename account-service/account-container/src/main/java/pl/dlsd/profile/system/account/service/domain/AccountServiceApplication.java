package pl.dlsd.profile.system.account.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "pl.dlsd.profile.system.account.service.dataaccess")
@EntityScan(basePackages = "pl.dlsd.profile.system.account.service.dataaccess")
@ComponentScan(basePackages = "pl.dlsd.profile.system")
@SpringBootApplication(scanBasePackages = "pl.dlsd.profile.system.account.service")
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
