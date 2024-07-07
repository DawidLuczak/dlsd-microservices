package pl.dlsd.profile.system.account.service.domain.ports.input.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountResponse;

import java.util.Optional;

public interface AccountApplicationService {
    CreateAccountResponse createAccount(@Valid CreateAccountCommand createAccountCommand);

    boolean accountExists(@Valid @NotNull String email);

    Optional<SignInAccountResponse> signInAccount(SignInAccountCommand signInAccountCommand);
}
