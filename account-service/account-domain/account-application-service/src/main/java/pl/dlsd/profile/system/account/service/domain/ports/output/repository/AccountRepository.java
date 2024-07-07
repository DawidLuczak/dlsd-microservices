package pl.dlsd.profile.system.account.service.domain.ports.output.repository;

import pl.dlsd.profile.system.account.service.domain.entity.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);

    Optional<Account> findById(UUID accountId);

    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Account> findByEmailAndPassword(String email, String password);

    List<Account> findAll();
}
