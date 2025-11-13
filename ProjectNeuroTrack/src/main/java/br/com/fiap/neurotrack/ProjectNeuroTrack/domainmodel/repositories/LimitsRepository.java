package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.Limits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitsRepository extends JpaRepository<Limits, Long> {
}
