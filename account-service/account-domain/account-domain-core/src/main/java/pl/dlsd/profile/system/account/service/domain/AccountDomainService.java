package pl.dlsd.profile.system.account.service.domain;

import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.event.AccountCreatedEvent;

public interface AccountDomainService {
    AccountCreatedEvent createAccount(Account account);
}
