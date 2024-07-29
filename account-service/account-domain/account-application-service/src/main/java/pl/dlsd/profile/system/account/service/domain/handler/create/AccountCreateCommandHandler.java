package pl.dlsd.profile.system.account.service.domain.handler.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.event.AccountCreatedEvent;
import pl.dlsd.profile.system.account.service.domain.mapper.AccountDataMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountCreateCommandHandler {
    private final AccountCreateHelper accountCreateHelper;
    private final AccountDataMapper accountDataMapper;

    public CreateAccountResponse createAccount(CreateAccountCommand createAccountCommand) {
        AccountCreatedEvent accountCreatedEvent = accountCreateHelper.persistAccount(createAccountCommand);
//        accountCreatedMessagePublisher.publish(accountCreatedEvent);
        return accountDataMapper.accountToCreateAccountResponse(accountCreatedEvent.getAccount());
    }

    public boolean accountExists(String email) {
        return accountCreateHelper.accountExists(email);
    }
}
