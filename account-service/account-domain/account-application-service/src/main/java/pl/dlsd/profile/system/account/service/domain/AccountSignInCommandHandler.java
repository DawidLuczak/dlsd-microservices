package pl.dlsd.profile.system.account.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountResponse;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.exception.AccountDomainException;
import pl.dlsd.profile.system.account.service.domain.mapper.AccountDataMapper;
import pl.dlsd.profile.system.account.service.domain.ports.output.repository.AccountRepository;

import java.util.Optional;

@Slf4j
@Component
public class AccountSignInCommandHandler {
    private final AccountDataMapper accountDataMapper;
    private final AccountRepository accountRepository;
    private final AccountDomainService accountDomainService;

    public AccountSignInCommandHandler(AccountDataMapper accountDataMapper, AccountRepository accountRepository, AccountDomainService accountDomainService) {
        this.accountDataMapper = accountDataMapper;
        this.accountRepository = accountRepository;
        this.accountDomainService = accountDomainService;
    }

    public SignInAccountResponse signIn(SignInAccountCommand signInAccountCommand) {
        Optional<Account> accountResult = accountRepository.findByEmailAndPassword(signInAccountCommand.getEmail(), signInAccountCommand.getPassword());
        if (!accountResult.isPresent()) {
            throw new AccountDomainException("");
        }
        return accountResult.map(accountDataMapper::accountToSignInAccountResponse).orElse(null);
    }
}
