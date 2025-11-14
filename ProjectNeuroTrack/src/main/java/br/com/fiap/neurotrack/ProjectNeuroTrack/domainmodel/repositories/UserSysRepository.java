package br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.UserSys;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserSysRepository extends JpaRepository<UserSys, Long> {

    Page<UserSys> findAll(Pageable pageable);
    Optional<UserDetails> findUserByEmail(String username);

}
