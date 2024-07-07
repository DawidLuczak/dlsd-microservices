package pl.dlsd.profile.system.account.service.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dlsd.profile.system.account.service.dataaccess.entity.AccountEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, UUID> {
    boolean existsAccountByEmail(String email);

    Optional<AccountEntity> findByEmailAndPassword(String email, String password);
}
