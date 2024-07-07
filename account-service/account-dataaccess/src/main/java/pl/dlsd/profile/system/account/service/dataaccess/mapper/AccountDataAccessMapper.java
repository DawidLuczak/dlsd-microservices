package pl.dlsd.profile.system.account.service.dataaccess.mapper;

import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.dataaccess.entity.AccountEntity;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.valueobject.Credentials;

@Component
public class AccountDataAccessMapper {
    public Account accountEntityToAccount(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .createdAt(accountEntity.getCreatedAt())
                .credentials(new Credentials(accountEntity.getEmail(), accountEntity.getPassword()))
                .build();
    }

    public AccountEntity accountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId().getValue())
                .createdAt(account.getCreatedAt())
                .email(account.getCredentials().getEmail())
                .password(account.getCredentials().getPassword())
                .build();
    }
}
