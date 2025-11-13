package br.com.fiap.neurotrack.ProjectNeuroTrack.service;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.UserSys;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserSysService {

    Page<UserSys> findAll(int pagina, int itens);
    Optional<UserSys> findById(Long id);
    UserSys save(UserSys userSys);
    boolean existsById(Long id);
    void deleteById(Long id);
    void delete(UserSys userSys);
    UserSys partialUpdate(Long id, UserSys userSys);
}
