package pl.dlsd.profile.system.account.service.dataaccess.adapter;

import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.dataaccess.mapper.AccountDataAccessMapper;
import pl.dlsd.profile.system.account.service.dataaccess.repository.AccountJpaRepository;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.ports.output.repository.AccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository accountJpaRepository;
    private final AccountDataAccessMapper accountDataAccessMapper;

    public AccountRepositoryImpl(AccountJpaRepository accountJpaRepository, AccountDataAccessMapper accountDataAccessMapper) {
        this.accountJpaRepository = accountJpaRepository;
        this.accountDataAccessMapper = accountDataAccessMapper;
    }

    @Override
    public Account save(Account account) {
        return accountDataAccessMapper.accountEntityToAccount(
                accountJpaRepository.save(
                        accountDataAccessMapper.accountToAccountEntity(account)));
    }

    @Override
    public Optional<Account> findById(UUID accountId) {
        return accountJpaRepository.findById(accountId)
                .map(accountDataAccessMapper::accountEntityToAccount);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(String email) {
        return accountJpaRepository.existsAccountByEmail(email);
    }

    @Override
    public Optional<Account> findByEmailAndPassword(String email, String password) {
        return accountJpaRepository.findByEmailAndPassword(email, password).map(accountDataAccessMapper::accountEntityToAccount);
    }

    @Override
    public List<Account> findAll() {
        return accountJpaRepository.findAll()
                .stream()
                .map(accountDataAccessMapper::accountEntityToAccount)
                .toList();
    }
}
