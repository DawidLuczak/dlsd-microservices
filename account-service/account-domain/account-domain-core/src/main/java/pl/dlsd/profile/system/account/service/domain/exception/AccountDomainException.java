package pl.dlsd.profile.system.account.service.domain.exception;

import pl.dlsd.profile.system.domain.exception.DomainException;

public class AccountDomainException extends DomainException {

    public AccountDomainException(String message) {
        super(message);
    }

    public AccountDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
