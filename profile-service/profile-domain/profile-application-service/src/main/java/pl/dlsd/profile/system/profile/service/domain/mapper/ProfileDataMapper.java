package pl.dlsd.profile.system.profile.service.domain.mapper;

import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.domain.valueobject.AccountId;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileCommand;
import pl.dlsd.profile.system.profile.service.domain.dto.create.CreateProfileResponse;
import pl.dlsd.profile.system.profile.service.domain.dto.get.GetProfileResponse;
import pl.dlsd.profile.system.profile.service.domain.entity.Profile;

import java.util.UUID;

@Component
public class ProfileDataMapper {

    public Profile createProfileCommandToProfile(CreateProfileCommand createProfileCommand) {
        return Profile
                .builder()
                .id(UUID.randomUUID())
                .accountId(new AccountId(UUID.randomUUID()))
                .nickname(createProfileCommand.getNickname())
                .build();
    }


    public CreateProfileResponse profileToCreateProfileResponse(Profile profile) {
        return CreateProfileResponse.builder()
                .profileId(profile.getId().getValue())
                .build();
    }

    public GetProfileResponse profileToGetProfileResponse(Profile profile) {
        return GetProfileResponse.builder()
                .nickname(profile.getNickname())
                .build();
    }
}
