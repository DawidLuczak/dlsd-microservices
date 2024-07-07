package pl.dlsd.profile.system.account.service.application.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.CreateAccountResponse;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountCommand;
import pl.dlsd.profile.system.account.service.domain.dto.create.SignInAccountResponse;
import pl.dlsd.profile.system.account.service.domain.ports.input.service.AccountApplicationService;

import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value="/accounts", produces = "application/vnd.api.v1+json")
public class AccountController {

    private final AccountApplicationService accountApplicationService;

    public AccountController(AccountApplicationService accountApplicationService) {
        this.accountApplicationService = accountApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountCommand createAccountCommand) {
        log.info("Creating account");
        CreateAccountResponse createProfileResponse = accountApplicationService.createAccount(createAccountCommand);
        log.info("Account created with id: {}", createProfileResponse.getId());
        return ResponseEntity.ok(createProfileResponse);
    }

    @PostMapping(value = "/exists")
    public ResponseEntity<Boolean> accountExists(@RequestBody String email) {
        boolean accountExists = accountApplicationService.accountExists(email);
        return ResponseEntity.ok(accountExists);
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<SignInAccountResponse> signIn(@RequestBody SignInAccountCommand signInAccountCommand) {
        log.info("Sign in account");
        try {
            Optional<SignInAccountResponse> signInAccountResponse = accountApplicationService.signInAccount(signInAccountCommand);
            return signInAccountResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
