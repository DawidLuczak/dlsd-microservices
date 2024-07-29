package pl.dlsd.profile.system.account.service.application.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.dlsd.profile.system.account.service.domain.dto.activate.ActivateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.dto.authenticate.AuthenticateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.authenticate.AuthenticateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.entity.Account;
import pl.dlsd.profile.system.account.service.domain.ports.input.service.AccountApplicationService;

import java.net.URI;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value="/auth", produces = "application/vnd.api.v1+json")
@RequiredArgsConstructor
public class AccountController {
    private final AccountApplicationService accountApplicationService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateAccountCommand signInAccountCommand)
            throws AuthenticationException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInAccountCommand.getEmail(),
                            signInAccountCommand.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AuthenticateAccountResponse loginResponse = accountApplicationService.authenticate(authentication);
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountCommand createAccountCommand) {
        log.info("Creating account");
        CreateAccountResponse createProfileResponse = accountApplicationService.createAccount(createAccountCommand);
        log.info("Account created with id: {}", createProfileResponse.getId());
        return ResponseEntity.created(URI.create(createProfileResponse.getId().toString())).build();
    }

    @GetMapping("/activate")
    public void activateAccount(@RequestParam(value = "key") String key) {
        ActivateAccountCommand activateAccountCommand = ActivateAccountCommand.builder().activationKey(key).build();
        accountApplicationService.activateAccount(activateAccountCommand);
    }

    @PostMapping("/exists")
    public ResponseEntity<Boolean> accountExists(@RequestBody String email) {
        boolean accountExists = accountApplicationService.accountExists(email);
        return ResponseEntity.ok(accountExists);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = accountApplicationService.getAccounts();
        return ResponseEntity.ok(accounts);
    }
}
