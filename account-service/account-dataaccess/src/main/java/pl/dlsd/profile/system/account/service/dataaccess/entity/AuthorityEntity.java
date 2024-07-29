package pl.dlsd.profile.system.account.service.dataaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
@Entity
public class AuthorityEntity {
    @Id
    private int id;
    private String name;
}
