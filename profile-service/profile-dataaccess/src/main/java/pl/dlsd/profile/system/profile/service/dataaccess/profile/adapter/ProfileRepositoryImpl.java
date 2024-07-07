package pl.dlsd.profile.system.profile.service.dataaccess.profile.adapter;

import org.springframework.stereotype.Component;
import pl.dlsd.profile.system.profile.service.dataaccess.profile.mapper.ProfileDataAccessMapper;
import pl.dlsd.profile.system.profile.service.dataaccess.profile.repository.ProfileJpaRepository;
import pl.dlsd.profile.system.profile.service.domain.entity.Profile;
import pl.dlsd.profile.system.profile.service.domain.ports.output.repository.ProfileRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProfileRepositoryImpl implements ProfileRepository {
    private final ProfileJpaRepository profileJpaRepository;
    private final ProfileDataAccessMapper profileDataAccessMapper;

    public ProfileRepositoryImpl(ProfileJpaRepository profileJpaRepository, ProfileDataAccessMapper profileDataAccessMapper) {
        this.profileJpaRepository = profileJpaRepository;
        this.profileDataAccessMapper = profileDataAccessMapper;
    }

    @Override
    public Profile save(Profile profile) {
        return profileDataAccessMapper.profileEntityToProfile(
                profileJpaRepository.save(
                        profileDataAccessMapper.profileToProfileEntity(profile)
                )
        );
    }

    @Override
    public Optional<Profile> findById(UUID profileId) {
        return profileJpaRepository.findById(profileId)
                .map(profileDataAccessMapper::profileEntityToProfile);
    }

    @Override
    public List<Profile> findAll() {
        return profileJpaRepository.findAll()
                .stream()
                .map(profileDataAccessMapper::profileEntityToProfile)
                .collect(Collectors.toList());
    }
}
