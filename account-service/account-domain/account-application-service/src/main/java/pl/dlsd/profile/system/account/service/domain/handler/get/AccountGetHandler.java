package pl.dlsd.profile.system.account.service.domain.handler.get;

import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.ports.output.repository.AccountRepository;

import java.util.List;

@Component
public class AccountGetHandler {
    private final AccountRepository accountRepository;

    public AccountGetHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
