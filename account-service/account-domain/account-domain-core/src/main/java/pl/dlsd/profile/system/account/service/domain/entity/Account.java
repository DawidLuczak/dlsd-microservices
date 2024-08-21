package pl.dlsd.profile.system.account.service.domain.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.dlsd.profile.system.account.service.domain.valueobject.Credentials;
import pl.dlsd.profile.system.domain.entity.AggregateRoot;
import pl.dlsd.profile.system.domain.valueobject.AccountId;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
public class Account extends AggregateRoot<AccountId> implements UserDetails {
    private final Credentials credentials;
    private final String activationKey;
    private final boolean active;
    private final Date createdAt;
    private final List<Authority> authorities;

    public Account(Credentials credentials, String activationKey, boolean active, Date createdAt, List<Authority> authorities) {
        this.credentials = credentials;
        this.activationKey = activationKey;
        this.active = active;
        this.createdAt = createdAt;
        this.authorities = authorities;
    }

    public Account(Builder builder) {
        setId(builder.id);
        credentials = builder.credentials;
        createdAt = builder.createdAt;
        activationKey = builder.activationKey;
        active = builder.active;
        authorities = builder.authorities;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public List<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return credentials.getPassword();
    }

    @Override
    public String getUsername() {
        return credentials.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public static final class Builder {
        private AccountId id;
        private Credentials credentials;
        private String activationKey;
        private boolean active;
        private Date createdAt;
        private List<Authority> authorities;

        private Builder() {}

        public Account.Builder id(UUID val) {
            id = new AccountId(val);
            return this;
        }

        public Account.Builder createdAt(Date val) {
            createdAt = val;
            return this;
        }

        public Account.Builder credentials(Credentials val) {
            credentials = val;
            return this;
        }

        public Account.Builder activationKey(String val) {
            activationKey = val;
            return this;
        }

        public Account.Builder active(boolean val) {
            active = val;
            return this;
        }

        public Account.Builder authorities(List<Authority> val) {
            authorities = val;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
