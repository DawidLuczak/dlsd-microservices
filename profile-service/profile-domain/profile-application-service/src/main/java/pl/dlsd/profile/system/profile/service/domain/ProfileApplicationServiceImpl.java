package pl.dlsd.profile.system.profile.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileCommand;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileResponse;
import pl.dlsd.profile.system.profile.service.domain.dto.get.GetProfileQuery;
import pl.dlsd.profile.system.profile.service.domain.dto.get.GetProfileResponse;
import pl.dlsd.profile.system.profile.service.domain.ports.input.service.ProfileApplicationService;

import java.util.List;

@Slf4j
@Validated
@Service
class ProfileApplicationServiceImpl implements ProfileApplicationService {

    private final ProfileCreateCommandHandler profileCreateCommandHandler;

    private final ProfileGetQueryHandler profileGetQueryHandler;

    ProfileApplicationServiceImpl(ProfileCreateCommandHandler profileCreateCommandHandler, ProfileGetQueryHandler profileGetQueryHandler) {
        this.profileCreateCommandHandler = profileCreateCommandHandler;
        this.profileGetQueryHandler = profileGetQueryHandler;
    }


    @Override
    public CreateProfileResponse createProfile(CreateProfileCommand createProfileCommand) {
        return profileCreateCommandHandler.createProfile(createProfileCommand);
    }

    @Override
    public GetProfileResponse getProfile(GetProfileQuery getProfileQuery) {
        return profileGetQueryHandler.getProfile(getProfileQuery);
    }

    @Override
    public List<GetProfileResponse> getProfiles() {
        return profileGetQueryHandler.getProfiles();
    }
}
