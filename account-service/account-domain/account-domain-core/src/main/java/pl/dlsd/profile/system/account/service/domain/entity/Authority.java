package pl.dlsd.profile.system.account.service.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Builder
@AllArgsConstructor
public class Authority implements GrantedAuthority {
    @Getter
    private int id;
    private final String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}
