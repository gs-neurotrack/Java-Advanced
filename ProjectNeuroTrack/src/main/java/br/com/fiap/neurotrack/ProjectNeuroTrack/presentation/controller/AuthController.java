package br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.controller;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.UserSys;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.LimitsRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.RoleRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.UserSysRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.infrastructure.config.TokenConfig;
import br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.request.LoginRequest;
import br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.request.RegisterUserRequest;
import br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.response.LoginResponse;
import br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.response.RegisterUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication to User")
public class AuthController {

    private final UserSysRepository userSysRepository;
    private final RoleRepository roleRepository;
    private final LimitsRepository limitsRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(UserSysRepository userSysRepository, RoleRepository roleRepository, LimitsRepository limitsRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.userSysRepository = userSysRepository;
        this.roleRepository = roleRepository;
        this.limitsRepository = limitsRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @Operation(summary = "Fazer login de um usuário", method = "POST")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UserSys user = (UserSys) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @Operation(summary = "Registrar um usuário", method = "POST")
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {

        var role = roleRepository.findById(request.roleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found for ID: " + request.roleId()));

        var limits = limitsRepository.findById(request.limitsId())
                .orElseThrow(() -> new IllegalArgumentException("Limits not found for ID: " + request.limitsId()));

        UserSys newUser = new UserSys();
        newUser.setName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setStatus(request.status());
        newUser.setRole(role);
        newUser.setLimits(limits);

        userSysRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterUserResponse(newUser.getName(), newUser.getEmail()));
    }

}
