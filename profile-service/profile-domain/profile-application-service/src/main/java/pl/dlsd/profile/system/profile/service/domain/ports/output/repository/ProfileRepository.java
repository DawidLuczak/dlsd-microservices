package pl.dlsd.profile.system.profile.service.domain.ports.output.repository;

import pl.dlsd.profile.system.profile.service.domain.entity.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository {
    Profile save(Profile profile);

    Optional<Profile> findById(UUID profileId);

    List<Profile> findAll();
}
