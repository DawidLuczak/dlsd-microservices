package pl.dlsd.profile.system.profile.service.dataaccess.profile.mapper;

import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.profile.service.dataaccess.profile.entity.ProfileEntity;
import pl.dlsd.profile.system.profile.service.domain.entity.Profile;

@Component
public class ProfileDataAccessMapper {

    public ProfileEntity profileToProfileEntity(Profile profile) {
        return ProfileEntity.builder()
                .id(profile.getId().getValue())
                .nickname(profile.getNickname())
                .build();
    }

    public Profile profileEntityToProfile(ProfileEntity profileEntity) {
        return Profile
                .builder()
                .id(profileEntity.getId())
                .nickname(profileEntity.getNickname())
                .build();
    }
}
