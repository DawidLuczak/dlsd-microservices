package pl.dlsd.profile.system.domain.valueobject;

import java.util.UUID;

public class ProfileId extends BaseId<UUID> {
    public ProfileId(UUID value) {
        super(value);
    }
}
