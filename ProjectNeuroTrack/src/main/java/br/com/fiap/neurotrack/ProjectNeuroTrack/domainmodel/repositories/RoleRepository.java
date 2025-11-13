package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
