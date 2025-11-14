package br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.controller;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.UserSys;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.LimitsRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.RoleRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.UserSysRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.request.LoginRequest;
import br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.request.RegisterUserRequest;
import br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.response.LoginResponse;
import br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.response.RegisterUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserSysRepository userSysRepository;
    private final RoleRepository roleRepository;
    private final LimitsRepository limitsRepository;

    public AuthController(UserSysRepository userSysRepository, RoleRepository roleRepository, LimitsRepository limitsRepository) {
        this.userSysRepository = userSysRepository;
        this.roleRepository = roleRepository;
        this.limitsRepository = limitsRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return null;
    }

    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {

        var role = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found for ID: " + request.roleId()));

        var limits = limitsRepository.findById(request.limitsId())
                .orElseThrow(() -> new IllegalArgumentException("Limits not found for ID: " + request.limitsId()));

        UserSys newUser = new UserSys();
        newUser.setName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(request.password());
        newUser.setRole(role);
        newUser.setLimits(limits);

        userSysRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterUserResponse(newUser.getName(), newUser.getEmail()));
    }

}
