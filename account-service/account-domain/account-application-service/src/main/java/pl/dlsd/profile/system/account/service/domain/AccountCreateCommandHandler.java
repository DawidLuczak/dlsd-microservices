package pl.dlsd.profile.system.account.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.event.AccountCreatedEvent;
import pl.dlsd.profile.system.account.service.domain.mapper.AccountDataMapper;

@Slf4j
@Component
public class AccountCreateCommandHandler {
    private final AccountCreateHelper accountCreateHelper;
    private final AccountDataMapper accountDataMapper;

    public AccountCreateCommandHandler(AccountCreateHelper accountCreateHelper, AccountDataMapper accountDataMapper) {
        this.accountCreateHelper = accountCreateHelper;
        this.accountDataMapper = accountDataMapper;
//        this.accountCreatedMessagePublisher = accountCreatedMessagePublisher;
    }

    public CreateAccountResponse createAccount(CreateAccountCommand createAccountCommand) {
        AccountCreatedEvent accountCreatedEvent = accountCreateHelper.persistAccount(createAccountCommand);
//        accountCreatedMessagePublisher.publish(accountCreatedEvent);
        return accountDataMapper.accountToCreateAccountResponse(accountCreatedEvent.getAccount());
    }

    public boolean accountExists(String email) {
        return accountCreateHelper.accountExists(email);
    }
}
