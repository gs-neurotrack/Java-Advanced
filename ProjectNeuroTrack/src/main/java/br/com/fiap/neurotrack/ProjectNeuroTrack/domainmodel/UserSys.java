package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GS_USER_SYS")
public class UserSys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private @Getter @Setter Long id;

    @Column(name = "NAME_USER", nullable = false, length = 100)
    private @Getter @Setter String name;

    @Column(name = "EMAIL_USER", unique = true, length = 100)
    private @Getter @Setter String email;

    @Column(name = "PASSWORD_USER", nullable = false, length = 100)
    private @Getter @Setter String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ROLE")
    private @Getter @Setter Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_LIMITS")
    private @Getter @Setter Limits limits;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSys user = (UserSys) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return "UserSys{id=" + id + ", name='" + name + "'}"; }
}

