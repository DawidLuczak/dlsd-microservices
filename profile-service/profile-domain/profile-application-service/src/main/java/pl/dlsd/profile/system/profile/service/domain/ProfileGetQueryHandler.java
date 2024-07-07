package pl.dlsd.profile.system.profile.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.profile.service.domain.dto.get.GetProfileQuery;
import pl.dlsd.profile.system.profile.service.domain.dto.get.GetProfileResponse;
import pl.dlsd.profile.system.profile.service.domain.entity.Profile;
import pl.dlsd.profile.system.profile.service.domain.exception.ProfileDomainException;
import pl.dlsd.profile.system.profile.service.domain.mapper.ProfileDataMapper;
import pl.dlsd.profile.system.profile.service.domain.ports.output.repository.ProfileRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ProfileGetQueryHandler {

    private final ProfileRepository profileRepository;
    private final ProfileDataMapper profileDataMapper;

    public ProfileGetQueryHandler(ProfileRepository profileRepository, ProfileDataMapper profileDataMapper) {
        this.profileRepository = profileRepository;
        this.profileDataMapper = profileDataMapper;
    }

    public GetProfileResponse getProfile(GetProfileQuery getProfileQuery) {
        Optional<Profile> profile = profileRepository.findById(getProfileQuery.getProfileId());
        if (profile.isEmpty()) {
            log.info("Profile with id {} not found", getProfileQuery.getProfileId().toString());
            throw new ProfileDomainException("Profile not found");
        }
        return profileDataMapper.profileToGetProfileResponse(profile.get());
    }

    public List<GetProfileResponse> getProfiles() {
        return profileRepository.findAll()
                .stream()
                .map(profileDataMapper::profileToGetProfileResponse)
                .toList();
    }
}
