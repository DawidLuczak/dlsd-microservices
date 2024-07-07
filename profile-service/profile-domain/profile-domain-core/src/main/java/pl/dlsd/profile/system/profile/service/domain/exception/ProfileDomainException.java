package pl.dlsd.profile.system.profile.service.domain.exception;

import pl.dlsd.profile.system.domain.exception.DomainException;

public class ProfileDomainException extends DomainException {

    public ProfileDomainException(String message) {
        super(message);
    }

    public ProfileDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
