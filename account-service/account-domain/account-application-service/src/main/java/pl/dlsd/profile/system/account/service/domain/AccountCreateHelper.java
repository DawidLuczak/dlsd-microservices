package pl.dlsd.profile.system.account.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.event.AccountCreatedEvent;
import pl.dlsd.profile.system.account.service.domain.exception.AccountDomainException;
import pl.dlsd.profile.system.account.service.domain.mapper.AccountDataMapper;
import pl.dlsd.profile.system.account.service.domain.ports.output.repository.AccountRepository;

@Slf4j
@Component
public class AccountCreateHelper {
    private final AccountDataMapper accountDataMapper;
    private final AccountDomainService accountDomainService;
    private final AccountRepository accountRepository;

    public AccountCreateHelper(AccountDataMapper accountDataMapper, AccountDomainService accountDomainService, AccountRepository accountRepository) {
        this.accountDataMapper = accountDataMapper;
        this.accountDomainService = accountDomainService;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountCreatedEvent persistAccount(CreateAccountCommand createAccountCommand) {
        checkAccountEmail(createAccountCommand.getEmail());
        Account account = accountDataMapper.createAccountCommandToAccount(createAccountCommand);
        AccountCreatedEvent accountCreatedEvent = accountDomainService.createAccount(account);
        saveAccount(account);
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

    private Account saveAccount(Account account) {
        Account accountResult = accountRepository.save(account);
        if (accountResult == null) {
            log.error("Could not save account");
            throw new AccountDomainException("Could not save account");
        }
        log.info("Account is saved with id: {}", accountResult.getId().getValue());
        return accountResult;
    }
}
