package pl.dlsd.profile.system.profile.service.application.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.dlsd.profile.system.application.handler.ErrorDTO;
import pl.dlsd.profile.system.application.handler.GlobalExceptionHandler;
import pl.dlsd.profile.system.profile.service.domain.exception.ProfileDomainException;

@Slf4j
@ControllerAdvice
public class ProfileGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {ProfileDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ProfileDomainException profileDomainException) {
        log.error(profileDomainException.getMessage(), profileDomainException);
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(profileDomainException.getMessage())
                .build();
    }

//    @ResponseBody
//    @ExceptionHandler(value = {ProfileDomainException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorDTO handleException(ProfileNotFoundException profileNotFoundException) {
//        log.error(profileNotFoundException.getMessage(), profileNotFoundException);
//        return ErrorDTO.builder()
//                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
//                .message(profileNotFoundException.getMessage())
//                .build();
//    }
}
