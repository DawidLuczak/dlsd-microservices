package pl.dlsd.profile.system.profile.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class GetProfileResponse {
    private String nickname;
}
