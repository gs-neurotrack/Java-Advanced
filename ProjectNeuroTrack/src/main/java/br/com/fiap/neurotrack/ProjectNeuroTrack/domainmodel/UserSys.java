package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GS_USERS")
public class UserSys implements UserDetails {

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

    @Column(name = "STATUS", nullable = false, length = 1)
    private @Getter @Setter String status;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
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
        return true;
    }
}

