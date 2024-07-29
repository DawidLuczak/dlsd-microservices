package pl.dlsd.profile.system.account.service.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping(value="/auth/restricted", produces = "application/vnd.api.v1+json")
public class AuthenticationController {
    @GetMapping()
    public ResponseEntity<String> restricted() {
        return ResponseEntity.ok("access");
    }

    @GetMapping("/role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> role() {
        return ResponseEntity.ok("ADMIN");
    }
}
