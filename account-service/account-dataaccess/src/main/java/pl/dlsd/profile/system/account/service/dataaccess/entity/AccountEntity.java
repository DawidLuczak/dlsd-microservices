package pl.dlsd.profile.system.account.service.dataaccess.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class AccountEntity {
    @Id
    private UUID id;
    @Email
    private String email;
    private String password;
    private String activationKey;
    private boolean active;
    private Date createdAt;
    @ManyToMany
    @JoinTable(
            name = "account_authorities",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<AuthorityEntity> authorities;
}
