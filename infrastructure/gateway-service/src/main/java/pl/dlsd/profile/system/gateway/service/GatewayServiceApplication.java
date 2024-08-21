package pl.dlsd.profile.system.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan(basePackages = "pl.dlsd.profile.system")
@SpringBootApplication(scanBasePackages = "pl.dlsd.profile.system.gateway.service")
@EnableZuulProxy
public class GatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
