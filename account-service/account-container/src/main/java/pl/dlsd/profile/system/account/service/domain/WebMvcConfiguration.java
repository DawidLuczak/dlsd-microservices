package pl.dlsd.profile.system.account.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration {
    private final Environment env;

    WebMvcConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        String[] allowDomains = new String[2];
        allowDomains[0] = env.getProperty("services.host");
        allowDomains[1] = env.getProperty("services.profile-system-web-app");

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(allowDomains);
            }
        };
    }

    @Controller
    static class FaviconController {
        @RequestMapping("favicon.ico")
        @ResponseBody
        void favicon() {}
    }
}
