package pl.dlsd.profile.system.account.service.domain.mapper;

import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountResponse;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.valueobject.Credentials;

import java.util.Date;
import java.util.UUID;

@Component
public class AccountDataMapper {

    public Account createAccountCommandToAccount(CreateAccountCommand createAccountCommand) {
        return Account.builder()
                .id(UUID.randomUUID())
                .createdAt(new Date())
                .credentials(new Credentials(createAccountCommand.getEmail(), createAccountCommand.getPassword()))
                .build();
    }

    public CreateAccountResponse accountToCreateAccountResponse(Account account) {
        return CreateAccountResponse.builder()
                .id(account.getId().getValue())
                .build();
    }

    public Account signInAccountCommandToAccount(SignInAccountCommand signInAccountCommand) {
        return Account.builder()
                .id(UUID.randomUUID())
                .createdAt(new Date())
                .credentials(new Credentials(signInAccountCommand.getEmail(), signInAccountCommand.getPassword()))
                .build();
    }

    public SignInAccountResponse accountToSignInAccountResponse(Account account) {
        return SignInAccountResponse.builder()
                .id(account.getId().getValue())
                .build();
    }
}
