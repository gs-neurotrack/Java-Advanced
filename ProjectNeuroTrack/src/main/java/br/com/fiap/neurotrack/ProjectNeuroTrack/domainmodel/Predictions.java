package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PREDICTIONS")
public class Predictions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PREDICTION")
    private @Getter @Setter Long id;

    @Column(name = "STRESS_PREDICTED", nullable = false)
    private @Getter @Setter Double stressPredicted;

    @Column(name = "MESSAGE_RISK", length = 255)
    private @Getter @Setter String message;

    @Column(name = "DATE_PREDICTED")
    private @Getter @Setter LocalDate datePredicted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER")
    private @Getter @Setter UserSys user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_STATUS_RISK")
    private @Getter @Setter StatusRisk statusRisk;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Predictions that = (Predictions) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return "Predictions{id=" + id + ", stress=" + stressPredicted + "}"; }
}

