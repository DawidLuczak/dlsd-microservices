package pl.dlsd.profile.system.account.service.domain.handler.activate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.dto.activate.ActivateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.exception.AccountDomainException;
import pl.dlsd.profile.system.account.service.domain.mapper.AccountDataMapper;
import pl.dlsd.profile.system.account.service.domain.ports.output.repository.AccountRepository;

@Slf4j
@Component
public class AccountActivateCommandHandler {
    private final AccountDataMapper accountDataMapper;
    private final AccountRepository accountRepository;

    public AccountActivateCommandHandler(AccountDataMapper accountDataMapper, AccountRepository accountRepository) {
        this.accountDataMapper = accountDataMapper;
        this.accountRepository = accountRepository;
    }

    public Account activateAccount(ActivateAccountCommand activateAccountCommand) throws AccountDomainException {
        log.debug("Activating user for activation key {}", activateAccountCommand.getActivationKey());
        return accountRepository
                .findOneByActivationKey(activateAccountCommand.getActivationKey())
                .map(accountDataMapper::accountToActivatedAccount)
                .map(accountRepository::save)
                .orElseThrow(() -> new AccountDomainException("No user was found for this activation key"));
    }
}
