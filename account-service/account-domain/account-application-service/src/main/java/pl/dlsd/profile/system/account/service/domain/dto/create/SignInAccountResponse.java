package pl.dlsd.profile.system.account.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class SignInAccountResponse {
    private UUID id;
}
