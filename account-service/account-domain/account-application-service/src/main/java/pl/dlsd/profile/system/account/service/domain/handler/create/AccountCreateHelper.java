package pl.dlsd.profile.system.account.service.domain.handler.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.dlsd.profile.system.account.service.domain.AccountDomainService;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.event.AccountCreatedEvent;
import pl.dlsd.profile.system.account.service.domain.exception.AccountDomainException;
import pl.dlsd.profile.system.account.service.domain.mapper.AccountDataMapper;
import pl.dlsd.profile.system.account.service.domain.ports.output.repository.AccountRepository;
import pl.dlsd.profile.system.service.domain.MailService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountCreateHelper {
    private static final String ACTIVATION_KEY = "activationKey";

    private final AccountDataMapper accountDataMapper;
    private final AccountDomainService accountDomainService;
    private final AccountRepository accountRepository;
    private final MailService mailService;

    @Transactional
    public AccountCreatedEvent persistAccount(CreateAccountCommand createAccountCommand) {
        checkAccountEmail(createAccountCommand.getEmail());
        validatePasswords(createAccountCommand);
        Account account = accountDataMapper.createAccountCommandToAccount(createAccountCommand);
        AccountCreatedEvent accountCreatedEvent = accountDomainService.createAccount(account);
        saveAccount(account);
        sendActivationEmail(account.getCredentials().getEmail(), account.getActivationKey());
        return accountCreatedEvent;
    }

    public boolean accountExists(String email) {
        return accountRepository.existsByEmail(email);
    }

    private void checkAccountEmail(String email) {
        if (this.accountExists(email)) {
            throw new AccountDomainException("Email is already assigned to existing account");
        }
    }

    private void validatePasswords(CreateAccountCommand createAccountCommand) {
        if (!createAccountCommand.getPassword().matches("\\S*[a-z]\\S*")) {
            throw new AccountDomainException("Password does not contain lowercase");
        }

        if (!createAccountCommand.getPassword().matches("\\S*[A-Z]\\S*")) {
            throw new AccountDomainException("Password does not contain uppercase");
        }

        if (!createAccountCommand.getPassword().matches("\\S*[0-9]\\S*")) {
            throw new AccountDomainException("Password does not contain number");
        }

        if (!createAccountCommand.getPassword().matches("\\S*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]\\S*")) {
            throw new AccountDomainException("Password does not contain special character");
        }

        if (createAccountCommand.getPassword().length() < 8) {
            throw new AccountDomainException("Password is too short");
        }

        if (!createAccountCommand.getPassword().equals(createAccountCommand.getPasswordConfirm())) {
            throw new AccountDomainException("Passwords are not equal");
        }
    }

    private Account saveAccount(Account account) {
        Account accountResult = accountRepository.save(account);
        if (accountResult == null) {
            log.error("Could not save account");
            throw new AccountDomainException("Could not save account");
        }
        log.info("Account is saved with id: {}", accountResult.getId().getValue());
        return accountResult;
    }

    @Async
    private void sendActivationEmail(String email, String activationKey) {
        log.debug("Sending activation email to '{}'", email);
        Map<String, String> params = new HashMap<String, String>();
        params.put(ACTIVATION_KEY, activationKey);
        this.mailService.sendEmailFromTemplate(email, "mail/activationEmail", "email.activation.title", params);
    }
}
