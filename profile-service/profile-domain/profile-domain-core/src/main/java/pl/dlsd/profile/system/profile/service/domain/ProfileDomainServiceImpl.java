package pl.dlsd.profile.system.profile.service.domain;

import lombok.extern.slf4j.Slf4j;
import pl.dlsd.profile.system.profile.service.domain.entity.Profile;
import pl.dlsd.profile.system.profile.service.domain.event.ProfileCreatedEvent;
import pl.dlsd.profile.system.profile.service.domain.event.ProfileDeletedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class ProfileDomainServiceImpl implements ProfileDomainService {

    private static final String UTC = "UTC";

    @Override
    public ProfileCreatedEvent createProfile(Profile profile) {
        log.info("Profile with id {} created", profile.getId().getValue());
        return new ProfileCreatedEvent(profile, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public ProfileDeletedEvent deleteProfile(Profile profile) {
        log.info("Profile with id {} deleted", profile.getId().getValue());
        return new ProfileDeletedEvent(profile, ZonedDateTime.now(ZoneId.of(UTC)));
    }

}
