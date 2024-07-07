package pl.dlsd.profile.system.account.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "account-service")
public class AccountServiceConfigData {
    private String accountTopicName;
}
