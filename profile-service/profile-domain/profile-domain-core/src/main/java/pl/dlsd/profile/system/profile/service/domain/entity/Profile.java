package pl.dlsd.profile.system.profile.service.domain.entity;

import lombok.Getter;
import lombok.Setter;
import pl.dlsd.profile.system.domain.entity.AggregateRoot;
import pl.dlsd.profile.system.domain.valueobject.AccountId;
import pl.dlsd.profile.system.domain.valueobject.ProfileId;

import java.util.UUID;

@Getter
public class Profile extends AggregateRoot<ProfileId> {
    private final AccountId accountId;
    @Setter
    private String nickname;

    public Profile(AccountId accountId) {
        this.accountId = accountId;
    }

    private Profile(Builder builder) {
        super.setId(builder.id);
        this.accountId = builder.accountId;
        setNickname(builder.nickname);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ProfileId id;
        private AccountId accountId;
        private String nickname;

        private Builder() {}

        public Builder id(UUID val) {
            id = new ProfileId(val);
            return this;
        }

        public Builder accountId(AccountId val) {
            accountId = val;
            return this;
        }

        public Builder nickname(String val) {
            nickname = val;
            return this;
        }

        public Profile build() {
            return new Profile(this);
        }
    }
}
