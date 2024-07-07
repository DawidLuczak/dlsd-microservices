package pl.dlsd.profile.system.account.service.application.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.dlsd.profile.system.account.service.domain.exception.AccountDomainException;
import pl.dlsd.profile.system.application.handler.ErrorDTO;
import pl.dlsd.profile.system.application.handler.GlobalExceptionHandler;

@Slf4j
@ControllerAdvice
public class AccountGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {AccountDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(AccountDomainException profileDomainException) {
        log.error(profileDomainException.getMessage(), profileDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(profileDomainException.getMessage())
                .build();
    }
}
