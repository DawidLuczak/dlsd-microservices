package pl.dlsd.profile.system.profile.service.domain.event;

import pl.dlsd.profile.system.profile.service.domain.entity.Profile;

import java.time.ZonedDateTime;

public class ProfileCreatedEvent extends ProfileEvent {

    public ProfileCreatedEvent(Profile profile, ZonedDateTime createdAt) {
        super(profile, createdAt);
    }
}
