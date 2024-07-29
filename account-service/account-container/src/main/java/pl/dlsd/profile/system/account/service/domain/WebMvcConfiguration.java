package pl.dlsd.profile.system.account.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final Environment env;

    WebMvcConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("pl");
        return lci;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowDomains = new String[3];
        allowDomains[0] = env.getProperty("services.host");
        allowDomains[1] = env.getProperty("services.profile-system-web-app");
        allowDomains[2] = env.getProperty("services.api-gateway");
        registry.addMapping("/**").allowedOrigins(allowDomains);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer()
//    {
//        String[] allowDomains = new String[2];
//        allowDomains[0] = env.getProperty("services.host");
//        allowDomains[1] = env.getProperty("services.profile-system-web-app");
//
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins(allowDomains);
//            }
//        };
//    }

    @Controller
    static class FaviconController {
        @RequestMapping("favicon.ico")
        @ResponseBody
        void favicon() {}
    }
}
