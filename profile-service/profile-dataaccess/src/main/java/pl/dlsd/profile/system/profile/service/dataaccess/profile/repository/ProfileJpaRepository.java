package pl.dlsd.profile.system.profile.service.dataaccess.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dlsd.profile.system.profile.service.dataaccess.profile.entity.ProfileEntity;

import java.util.UUID;

@Repository
public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, UUID> {

}
