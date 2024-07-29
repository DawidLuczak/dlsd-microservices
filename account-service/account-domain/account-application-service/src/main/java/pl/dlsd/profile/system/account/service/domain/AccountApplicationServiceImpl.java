package pl.dlsd.profile.system.account.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.dlsd.profile.system.account.service.domain.handler.get.AccountGetHandler;
import pl.dlsd.profile.system.account.service.domain.ports.input.service.AccountApplicationService;
import pl.dlsd.profile.system.account.service.domain.handler.create.AccountCreateCommandHandler;
import pl.dlsd.profile.system.account.service.domain.handler.authenticate.AccountAuthenticationHandler;
import pl.dlsd.profile.system.account.service.domain.dto.activate.ActivateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.dto.authenticate.AuthenticateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.exception.AccountDomainException;
import pl.dlsd.profile.system.account.service.domain.handler.activate.AccountActivateCommandHandler;

import java.util.List;

@Slf4j
@Validated
@Service(value = "accountService")
public class AccountApplicationServiceImpl implements AccountApplicationService {
    private final AccountCreateCommandHandler accountCreateCommandHandler;
    private final AccountAuthenticationHandler accountAuthenticationHandler;
    private final AccountActivateCommandHandler accountActivateCommandHandler;
    private final AccountGetHandler accountGetHandler;

    public AccountApplicationServiceImpl(
            AccountCreateCommandHandler accountCreateCommandHandler,
            AccountAuthenticationHandler accountSignInCommandHandler,
            AccountActivateCommandHandler accountActivateCommandHandler,
            AccountGetHandler accountGetHandler
    ) {
        this.accountCreateCommandHandler = accountCreateCommandHandler;
        this.accountAuthenticationHandler = accountSignInCommandHandler;
        this.accountActivateCommandHandler = accountActivateCommandHandler;
        this.accountGetHandler = accountGetHandler;
    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountCommand createAccountCommand) {
        return accountCreateCommandHandler.createAccount(createAccountCommand);
    }

    @Override
    public boolean accountExists(String email) {
        return accountCreateCommandHandler.accountExists(email);
    }


    @Override
    public Account activateAccount(ActivateAccountCommand activateAccountCommand) throws AccountDomainException {
        return accountActivateCommandHandler.activateAccount(activateAccountCommand);
    }

    @Override
    public AuthenticateAccountResponse authenticate(Authentication authentication) {
        return accountAuthenticationHandler.authenticate(authentication);
    }

    @Override
    public List<Account> getAccounts() {
        return accountGetHandler.getAccounts();
    }
}
