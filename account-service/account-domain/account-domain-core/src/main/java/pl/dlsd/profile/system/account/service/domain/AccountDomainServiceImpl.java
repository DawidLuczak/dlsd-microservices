package pl.dlsd.profile.system.account.service.domain;

import lombok.extern.slf4j.Slf4j;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.event.AccountCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class AccountDomainServiceImpl implements AccountDomainService {
    private static final String UTC = "UTC";

    @Override
    public AccountCreatedEvent createAccount(Account account) {
        log.info("Profile with id {} created", account.getId().getValue());
        return new AccountCreatedEvent(account, ZonedDateTime.now(ZoneId.of(UTC)));
    }


}
