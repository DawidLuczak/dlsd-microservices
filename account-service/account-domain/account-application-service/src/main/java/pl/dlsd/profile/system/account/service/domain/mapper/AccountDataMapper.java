package pl.dlsd.profile.system.account.service.domain.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.dto.authenticate.AuthenticateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.authenticate.AuthenticateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.valueobject.Credentials;
import pl.dlsd.profile.system.utils.RandomUtil;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountDataMapper {
    private final PasswordEncoder passwordEncoder;

    public Account createAccountCommandToAccount(CreateAccountCommand createAccountCommand) {
        return Account.builder()
                .id(UUID.randomUUID())
                .createdAt(new Date())
                .credentials(
                        new Credentials(
                                createAccountCommand.getEmail(),
                                passwordEncoder.encode(createAccountCommand.getPassword())))
                .activationKey(RandomUtil.generateActivationKey())
                .build();
    }

    public CreateAccountResponse accountToCreateAccountResponse(Account account) {
        return CreateAccountResponse.builder()
                .id(account.getId().getValue())
                .build();
    }

    public Account accountToActivatedAccount(Account account) {
        return Account.builder()
                .id(account.getId().getValue())
                .credentials(account.getCredentials())
                .createdAt(account.getCreatedAt())
                .active(true)
                .activationKey(null)
                .build();
    }
}
