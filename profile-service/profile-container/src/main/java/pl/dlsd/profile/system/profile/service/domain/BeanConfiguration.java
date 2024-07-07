package pl.dlsd.profile.system.profile.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ProfileDomainService profileDomainService() {
        return new ProfileDomainServiceImpl();
    }
}
