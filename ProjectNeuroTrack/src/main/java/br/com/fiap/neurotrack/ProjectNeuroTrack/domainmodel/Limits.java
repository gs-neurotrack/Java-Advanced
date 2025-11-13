package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GS_LIMITS")
public class Limits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIMITS")
    private @Getter @Setter Long id;

    @Column(name = "LIMIT_HOURS", nullable = false)
    private @Getter @Setter Double limitHours;

    @Column(name = "LIMIT_MEETINGS", nullable = false, length = 50)
    private @Getter @Setter String limitMeetings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Limits limits = (Limits) o;
        return Objects.equals(id, limits.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return "Limits{id=" + id + ", limitValue=" + limitHours + "}"; }
}

