package pl.dlsd.profile.system.account.service.domain.valueobject;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Credentials {
    private final String email;
    private final String password;

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private Credentials(Builder builder) {
        email = builder.email;
        password = builder.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }


    public static final class Builder {
        private final String email;
        private final String password;

        public Builder(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public Credentials build() {
            return new Credentials(this);
        }
    }
}
