package pl.dlsd.profile.system.account.service.domain.event;

import pl.dlsd.profile.system.account.service.domain.entity.Account;

import java.time.ZonedDateTime;

public class AccountCreatedEvent extends AccountEvent {
    public AccountCreatedEvent(Account account, ZonedDateTime createdAt) {
        super(account, createdAt);
    }
}
