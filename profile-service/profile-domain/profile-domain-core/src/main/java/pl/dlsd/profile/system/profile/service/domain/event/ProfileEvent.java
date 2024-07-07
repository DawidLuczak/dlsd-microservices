package pl.dlsd.profile.system.profile.service.domain.event;

import pl.dlsd.profile.system.domain.event.DomainEvent;
import pl.dlsd.profile.system.profile.service.domain.entity.Profile;

import java.time.ZonedDateTime;

public abstract class ProfileEvent extends DomainEvent<Profile> {
    private final Profile profile;
    private final ZonedDateTime createdAt;

    protected ProfileEvent(Profile profile, ZonedDateTime createdAt) {
        this.profile = profile;
        this.createdAt = createdAt;
    }

    public Profile getProfile() {
        return profile;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
