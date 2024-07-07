package pl.dlsd.profile.system.application.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorDTO {
    private final String code;
    private final String message;
}
