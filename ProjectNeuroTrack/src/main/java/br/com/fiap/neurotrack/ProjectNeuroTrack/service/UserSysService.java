package br.com.fiap.neurotrack.ProjectNeuroTrack.service;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.UserSys;

import java.util.List;
import java.util.Optional;

public interface UserSysService {

    List<UserSys> findAll();
    Optional<UserSys> findById(Long id);
    UserSys save(UserSys userSys);
    boolean existsById(Long id);
    void deleteById(Long id);
    void delete(UserSys userSys);
    UserSys update(UserSys userSys);

}
