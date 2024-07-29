package pl.dlsd.profile.system.account.service.domain.ports.input.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.Authentication;
import pl.dlsd.profile.system.account.service.domain.dto.activate.ActivateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.dto.authenticate.AuthenticateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.entity.Account;

import java.util.List;

public interface AccountApplicationService {
    CreateAccountResponse createAccount(@Valid CreateAccountCommand createAccountCommand);

    boolean accountExists(@Valid @NotNull String email);

    Account activateAccount(ActivateAccountCommand activateAccountCommand);

    AuthenticateAccountResponse authenticate(Authentication authentication);

    List<Account> getAccounts();
}
