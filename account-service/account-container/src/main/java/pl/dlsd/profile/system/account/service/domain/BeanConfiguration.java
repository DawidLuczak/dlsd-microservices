package pl.dlsd.profile.system.account.service.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public AccountDomainService profileDomainService() {
        return new AccountDomainServiceImpl();
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);


        mailSender.setUsername("dawid.luczak.it@gmail.com");
        mailSender.setPassword("ckye evxb lngt awul");
//        mailSender.setUsername("dlsd.devapps@gmail.com");
//        mailSender.setPassword("ujcy qfwo yiba vgfv");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
