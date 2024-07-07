package pl.dlsd.profile.system.account.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignInAccountCommand {
    @NotNull
    private final String email;
    @NotNull
    private final String password;
}