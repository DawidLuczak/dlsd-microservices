package pl.dlsd.profile.system.profile.service.domain.ports.input.service;

import jakarta.validation.Valid;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileCommand;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileResponse;
import pl.dlsd.profile.system.profile.service.domain.dto.get.GetProfileQuery;
import pl.dlsd.profile.system.profile.service.domain.dto.get.GetProfileResponse;

import java.util.List;

public interface ProfileApplicationService {

    CreateProfileResponse createProfile(@Valid CreateProfileCommand createProfileCommand);

    GetProfileResponse getProfile(@Valid GetProfileQuery getProfileQuery);

    List<GetProfileResponse> getProfiles();
}
