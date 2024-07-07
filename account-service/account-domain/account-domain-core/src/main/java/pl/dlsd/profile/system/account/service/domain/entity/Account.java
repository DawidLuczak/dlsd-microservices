package pl.dlsd.profile.system.account.service.domain.entity;

import lombok.Getter;
import pl.dlsd.profile.system.account.service.domain.valueobject.Credentials;
import pl.dlsd.profile.system.domain.entity.AggregateRoot;
import pl.dlsd.profile.system.domain.valueobject.AccountId;

import java.util.Date;
import java.util.UUID;

@Getter
public class Account extends AggregateRoot<AccountId> {
    private final Credentials credentials;
    private final Date createdAt;

    public Account(Credentials credentials, Date createdAt) {
        this.credentials = credentials;
        this.createdAt = createdAt;
    }

    public Account(Builder builder) {
        setId(builder.id);
        credentials = builder.credentials;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private AccountId id;
        private Credentials credentials;
        private Date createdAt;

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

        public Account build() {
            return new Account(this);
        }
    }
}
