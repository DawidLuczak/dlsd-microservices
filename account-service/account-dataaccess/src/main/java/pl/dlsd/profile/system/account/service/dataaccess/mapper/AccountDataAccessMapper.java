package pl.dlsd.profile.system.account.service.dataaccess.mapper;

import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.dataaccess.entity.AccountEntity;
import pl.dlsd.profile.system.account.service.dataaccess.entity.AuthorityEntity;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.entity.Authority;
import pl.dlsd.profile.system.account.service.domain.valueobject.Credentials;

import java.util.stream.Collectors;

@Component
public class AccountDataAccessMapper {
    public Account accountEntityToAccount(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .createdAt(accountEntity.getCreatedAt())
                .credentials(new Credentials(accountEntity.getEmail(), accountEntity.getPassword()))
                .activationKey(accountEntity.getActivationKey())
                .active(accountEntity.isActive())
                .authorities(
                        accountEntity.getAuthorities().stream()
                                .map(authorityEntity -> Authority.builder()
                                        .authority(authorityEntity.getName())
                                        .id(authorityEntity.getId())
                                        .build()
                                ).toList())
                .build();
    }

    public AccountEntity accountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId().getValue())
                .createdAt(account.getCreatedAt())
                .email(account.getCredentials().getEmail())
                .password(account.getCredentials().getPassword())
                .activationKey(account.getActivationKey())
                .active(account.isActive())
                .authorities(
                        account.getAuthorities().stream()
                                .map(authority -> AuthorityEntity.builder()
                                        .name(authority.getAuthority())
                                        .id(authority.getId())
                                        .build()
                                ).collect(Collectors.toSet()))
                .build();
    }

    public AccountEntity inactiveAccountEntityToActiveAccount(AccountEntity accountEntity) {
        accountEntity.setActivationKey(null);
        accountEntity.setActive(true);
        return accountEntity;
    }
}
