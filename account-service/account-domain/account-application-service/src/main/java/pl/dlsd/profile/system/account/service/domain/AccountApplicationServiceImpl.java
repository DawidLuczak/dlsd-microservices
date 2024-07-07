package pl.dlsd.profile.system.account.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountResponse;
import pl.dlsd.profile.system.account.service.domain.ports.input.service.AccountApplicationService;

import java.util.Optional;

@Slf4j
@Validated
@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {
    private final AccountCreateCommandHandler accountCreateCommandHandler;
    private final AccountSignInCommandHandler accountSignInCommandHandler;

    public AccountApplicationServiceImpl(AccountCreateCommandHandler accountCreateCommandHandler, AccountSignInCommandHandler accountSignInCommandHandler) {
        this.accountCreateCommandHandler = accountCreateCommandHandler;
        this.accountSignInCommandHandler = accountSignInCommandHandler;
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
    public Optional<SignInAccountResponse> signInAccount(SignInAccountCommand signInAccountCommand) {
        return Optional.of(accountSignInCommandHandler.signIn(signInAccountCommand));
    }
}
