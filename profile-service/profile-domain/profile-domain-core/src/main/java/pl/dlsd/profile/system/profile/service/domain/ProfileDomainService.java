package pl.dlsd.profile.system.profile.service.domain;

import pl.dlsd.profile.system.profile.service.domain.entity.Profile;
import pl.dlsd.profile.system.profile.service.domain.event.ProfileCreatedEvent;
import pl.dlsd.profile.system.profile.service.domain.event.ProfileDeletedEvent;

public interface ProfileDomainService {

    ProfileCreatedEvent createProfile(Profile profile);

    ProfileDeletedEvent deleteProfile(Profile profile);
}
