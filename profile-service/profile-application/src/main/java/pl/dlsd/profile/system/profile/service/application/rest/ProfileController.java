package pl.dlsd.profile.system.profile.service.application.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileCommand;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileResponse;
import pl.dlsd.profile.system.profile.service.domain.dto.get.GetProfileQuery;
import pl.dlsd.profile.system.profile.service.domain.dto.get.GetProfileResponse;
import pl.dlsd.profile.system.profile.service.domain.ports.input.service.ProfileApplicationService;

import java.util.List;
import java.util.UUID;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/profiles", produces = "application/vnd.api.v1+json")
public class ProfileController {

    private final ProfileApplicationService profileApplicationService;

    public ProfileController(ProfileApplicationService profileApplicationService) {
        this.profileApplicationService = profileApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateProfileResponse> createProfile(@RequestBody CreateProfileCommand createProfileCommand) {
        log.info("Creating profile");
        CreateProfileResponse createProfileResponse = profileApplicationService.createProfile(createProfileCommand);
        log.info("Profile created with id: {}", createProfileResponse.getProfileId());
        return ResponseEntity.ok(createProfileResponse);
    }

    @GetMapping
    public ResponseEntity<List<GetProfileResponse>> getProfiles() {
        List<GetProfileResponse> getProfilesResponse = profileApplicationService.getProfiles();
        log.info("Returning profiles");
        return ResponseEntity.ok(getProfilesResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProfileResponse> getProfileById(@PathVariable String id) {
        GetProfileResponse getProfileResponse = profileApplicationService.getProfile(GetProfileQuery.builder().profileId(UUID.randomUUID()).build());
        log.info("Returning profile with id {}", id);
        return ResponseEntity.ok(getProfileResponse);
    }
}
