package pl.dlsd.profile.system.account.service.domain.handler.authenticate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.account.service.domain.JwtService;
import pl.dlsd.profile.system.account.service.domain.dto.authenticate.AuthenticateAccountResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountAuthenticationHandler {
    private final JwtService jwtService;

    public AuthenticateAccountResponse authenticate(Authentication authentication) {
        String jwtToken = jwtService.generateJwtToken(authentication);
        return AuthenticateAccountResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getJwtExpirationMs())
                .build();
    }
}
