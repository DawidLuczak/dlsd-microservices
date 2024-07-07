package pl.dlsd.profile.system.profile.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileCommand;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileResponse;
import pl.dlsd.profile.system.profile.service.domain.entity.Profile;
import pl.dlsd.profile.system.profile.service.domain.event.ProfileCreatedEvent;
import pl.dlsd.profile.system.profile.service.domain.exception.ProfileDomainException;
import pl.dlsd.profile.system.profile.service.domain.mapper.ProfileDataMapper;
import pl.dlsd.profile.system.profile.service.domain.ports.output.repository.ProfileRepository;

import java.util.Optional;

@Slf4j
@Component
public class ProfileCreateCommandHandler {
    private final ProfileDomainService profileDomainService;
    private final ProfileRepository profileRepository;
    private final ProfileDataMapper profileDataMapper;

    public ProfileCreateCommandHandler(ProfileDomainService profileDomainService, ProfileRepository profileRepository, ProfileDataMapper profileDataMapper) {
        this.profileDomainService = profileDomainService;
        this.profileRepository = profileRepository;
        this.profileDataMapper = profileDataMapper;
    }

    @Transactional
    public CreateProfileResponse createProfile(CreateProfileCommand createProfileCommand) {
        Profile profile = profileDataMapper.createProfileCommandToProfile(createProfileCommand);
        ProfileCreatedEvent profileCreatedEvent = profileDomainService.createProfile(profile);
        Profile profileResult = saveProfile(profile);
        log.info("Profile is created with id: {}", profileResult.getId().getValue());
        return profileDataMapper.profileToCreateProfileResponse(profileResult);
    }


    private Profile saveProfile(Profile profile) {
        Profile profileResult = profileRepository.save(profile);
        if (profileResult == null) {
            log.error("Could not save profile");
            throw new ProfileDomainException("Could not save profile");
        }
        log.info("Profile is saved with id: {}", profileResult.getId().getValue());
        return profileResult;
    }
}
