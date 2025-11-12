package br.com.fiap.neurotrack.ProjectNeuroTrack.service;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.UserSys;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.UserSysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSysServiceImpl implements UserSysService {

    private final UserSysRepository userSysRepository;

    @Override
    public List<UserSys> findAll() {
        return new ArrayList<>(
                this.userSysRepository.findAll()
        );
    }

    @Override
    public Optional<UserSys> findById(Long id) {
        return this.userSysRepository.findById(id);
    }

    @Override
    public UserSys save(UserSys userSys) {
        return this.userSysRepository.save(userSys);
    }

    @Override
    public boolean existsById(Long id) {
        return this.userSysRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.userSysRepository.deleteById(id);
    }

    @Override
    public void delete(UserSys userSys) {
        this.userSysRepository.delete(userSys);
    }

    @Override
    public UserSys update(UserSys userSys) {
        return null;
    }
}
