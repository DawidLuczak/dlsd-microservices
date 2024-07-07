package pl.dlsd.profile.system.profile.service.dataaccess.profile.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
@Entity
public class ProfileEntity {
    @Id
    private UUID id;
    private String nickname;
}
