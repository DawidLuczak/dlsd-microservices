package pl.dlsd.profile.system.account.service.domain.event;

import lombok.Getter;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.domain.event.DomainEvent;

import java.time.ZonedDateTime;

@Getter
public abstract class AccountEvent extends DomainEvent<Account> {
    private final Account account;
    private final ZonedDateTime createdAt;

    protected AccountEvent(Account account, ZonedDateTime createdAt) {
        this.account = account;
        this.createdAt = createdAt;
    }
}
