package pl.dlsd.profile.system.account.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {


    @Bean
    public AccountDomainService profileDomainService() {
        return new AccountDomainServiceImpl();
    }

}
