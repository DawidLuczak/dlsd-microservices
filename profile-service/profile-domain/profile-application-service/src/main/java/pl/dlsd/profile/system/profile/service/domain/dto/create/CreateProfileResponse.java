package pl.dlsd.profile.system.profile.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateProfileResponse {
    private UUID profileId;
}
