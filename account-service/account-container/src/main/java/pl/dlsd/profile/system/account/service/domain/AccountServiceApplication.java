package pl.dlsd.profile.system.account.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.dlsd.profile.system.common.configuration.CommonProperties;
import pl.dlsd.profile.system.common.configuration.LocaleConfiguration;

@EnableJpaRepositories(basePackages = "pl.dlsd.profile.system.account.service.dataaccess")
@EntityScan(basePackages = "pl.dlsd.profile.system.account.service.dataaccess")
@ComponentScan(basePackages = "pl.dlsd.profile.system")
@SpringBootApplication(scanBasePackages = "pl.dlsd.profile.system.account.service")
@EnableConfigurationProperties({ CommonProperties.class })
@ImportAutoConfiguration({ LocaleConfiguration.class })
@EnableZuulProxy
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
