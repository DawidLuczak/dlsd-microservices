package pl.dlsd.profile.system.account.service.domain.dto.authenticate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class AuthenticateAccountResponse {
    private String token;
    private String username;
    private int expiresIn;
}
