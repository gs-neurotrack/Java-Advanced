package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SCORES")
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SCORES")
    private @Getter @Setter Long id;

    @Column(name = "SCORE_VALUE", nullable = false)
    private @Getter @Setter Double scoreValue;

    @Column(name = "TIME_RECOMMENDATION")
    private @Getter @Setter String timeRecommendation;

    @Column(name = "CREATED_AT")
    private @Getter @Setter LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_LOG")
    private @Getter @Setter DailyLogs dailyLogs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER")
    private @Getter @Setter UserSys user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores scores = (Scores) o;
        return Objects.equals(id, scores.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return "Scores{id=" + id + ", score=" + scoreValue + "}"; }
}

