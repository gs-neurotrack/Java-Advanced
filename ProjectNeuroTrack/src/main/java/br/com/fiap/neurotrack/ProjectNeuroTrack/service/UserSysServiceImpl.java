package br.com.fiap.neurotrack.ProjectNeuroTrack.service;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.Limits;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.Role;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.UserSys;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.LimitsRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.RoleRepository;
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
    private final RoleRepository roleRepository;  // Injeção do repository de Role
    private final LimitsRepository limitsRepository;  // Injeção do repository de Limits

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
        Role role = roleRepository.findById(userSys.getRole().getId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        Limits limits = limitsRepository.findById(userSys.getLimits().getId())
                .orElseThrow(() -> new IllegalArgumentException("Limits not found"));

        userSys.setRole(role);
        userSys.setLimits(limits);

        return userSysRepository.save(userSys);
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
    public UserSys partialUpdate(Long id, UserSys userSys) {
        if (!this.userSysRepository.existsById(id)) {
            throw new IllegalArgumentException("UserSys entity not found");
        }
        UserSys userFromDatabase = this.userSysRepository.findById(id).orElse(null);
        if (userFromDatabase == null) {
            throw new IllegalArgumentException("UserSys entity not found");
        }
        if (userSys.getName() != null && !userSys.getName().equals(userFromDatabase.getName())) {
            userFromDatabase.setName(userSys.getName());
        }
        if (userSys.getEmail() != null && !userSys.getEmail().equals(userFromDatabase.getEmail())) {
            userFromDatabase.setEmail(userSys.getEmail());
        }
        if (userSys.getPassword() != null && !userSys.getPassword().equals(userFromDatabase.getPassword())) {
            userFromDatabase.setPassword(userSys.getPassword());
        }
        if (userSys.getRole() != null && !userSys.getRole().equals(userFromDatabase.getRole())) {
            userFromDatabase.setRole(userSys.getRole());
        }
        if (userSys.getLimits() != null && !userSys.getLimits().equals(userFromDatabase.getLimits())) {
            userFromDatabase.setLimits(userSys.getLimits());
        }

        return this.userSysRepository.save(userFromDatabase);
    }
}
