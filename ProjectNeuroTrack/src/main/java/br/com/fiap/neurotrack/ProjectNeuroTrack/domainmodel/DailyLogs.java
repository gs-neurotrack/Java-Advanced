package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GS_DAILY_LOGS")
public class DailyLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOG")
    private @Getter @Setter Long id;

    @Column(name = "LOG_DATE", nullable = false)
    private @Getter @Setter LocalDate logDate;

    @Column(name = "LOG_DESC", length = 255)
    private @Getter @Setter String logDescription;

    @OneToMany(mappedBy = "dailyLogs", fetch = FetchType.LAZY)
    private @Getter @Setter List<Scores> scores;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyLogs that = (DailyLogs) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return "DailyLogs{id=" + id + ", logDate=" + logDate + "}"; }
}

