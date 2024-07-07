package pl.dlsd.profile.system.profile.service.domain.event;

import pl.dlsd.profile.system.profile.service.domain.entity.Profile;

import java.time.ZonedDateTime;

public class ProfileActivatedEvent extends ProfileEvent {
    protected ProfileActivatedEvent(Profile profile, ZonedDateTime createdAt) {
        super(profile, createdAt);
    }
}
