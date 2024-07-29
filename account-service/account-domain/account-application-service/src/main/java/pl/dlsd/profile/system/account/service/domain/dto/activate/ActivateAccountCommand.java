package pl.dlsd.profile.system.account.service.domain.dto.activate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ActivateAccountCommand {
    @NotNull
    private String activationKey;
}
