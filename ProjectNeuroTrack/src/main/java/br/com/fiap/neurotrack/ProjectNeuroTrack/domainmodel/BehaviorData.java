package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "GS_BEHAVIOR_DATA")
public class BehaviorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BEHAVIOR")
    private @Getter @Setter Long id;

    @Column(name = "BEHAVIOR_SPEED", nullable = false)
    private @Getter @Setter Double behaviorSpeed;

    @Column(name = "AVG_TYPING_SPEED", nullable = false)
    private @Getter @Setter Double avgTypingSpeed;

    @Column(name = "AVG_CLICK_SPEED", nullable = false)
    private @Getter @Setter Double avgClickSpeed;

    @Column(name = "AVG_TOUCH_DURATION", nullable = false)
    private @Getter @Setter Double avgTouchDuration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER")
    private @Getter @Setter UserSys user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BehaviorData that = (BehaviorData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() { return "BehaviorData{id=" + id + ", behaviorSpeed=" + behaviorSpeed + "}"; }
}

