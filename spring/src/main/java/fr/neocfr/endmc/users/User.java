package fr.neocfr.endmc.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player_profiles")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @JdbcType(VarcharJdbcType.class)
    @Column(nullable = false, unique = true)
    private UUID uniqueId;

    @Column(nullable = false)
    private String name;

}
