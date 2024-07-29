package pl.dlsd.profile.system.account.service.domain.dto.authenticate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AuthenticateAccountCommand {
    @NotNull
    private final String email;
    @NotNull
    private final String password;
}