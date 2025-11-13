package br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.controller;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.UserSys;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.LimitsRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.RoleRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.transferObjects.UserSysDTO;
import br.com.fiap.neurotrack.ProjectNeuroTrack.service.UserSysService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "CRUD operations to UserSys")
public class UserSysApiController {

    private final UserSysService userSysService;
    private final RoleRepository roleRepository;
    private final LimitsRepository limitsRepository;

    @Operation(summary = "Listar Todos os Usuarios", method = "GET")
    @GetMapping
    public ResponseEntity<Page<UserSys>> findAll(@RequestParam int pagina,
                                                 @RequestParam int itens) {
        Page<UserSys> usersPage = userSysService.findAll(pagina, itens);
        return ResponseEntity.ok(usersPage);
    }

    @Operation(summary = "Listar um usuário por ID", method = "GET")
    @GetMapping("/{id}") // http://localhost:8080/api/users/{id}
    public ResponseEntity<UserSysDTO> findById(@PathVariable("id") Long id) {
        Optional<UserSys> userOpt = this.userSysService.findById(id);
        return userOpt.map(user -> ResponseEntity.ok(UserSysDTO.fromEntity(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Insere Novo Usuario", method = "POST")
    @PostMapping
    public ResponseEntity<UserSysDTO> save(@Valid @RequestBody UserSysDTO userDto) {
        UserSys newUser = this.userSysService.save(UserSysDTO.toEntity(userDto, roleRepository, limitsRepository));
        return new ResponseEntity<>(UserSysDTO.fromEntity(newUser), HttpStatus.CREATED);
    }

    @Operation(summary = "Remove um usuário pelo ID", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!this.userSysService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        this.userSysService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Remove um usuário inteiro baseado no objeto User", method = "DELETE")
    @DeleteMapping("/removeObject")
    public ResponseEntity<Void> delete(@RequestBody UserSysDTO user) {
        if (!this.userSysService.existsById(user.getId())) {
            return ResponseEntity.notFound().build();
        }
        this.userSysService.delete(UserSysDTO.toEntity(user, roleRepository, limitsRepository));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza Totalmente o Usuario", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<UserSysDTO> update(@PathVariable("id") Long id, @Valid @RequestBody UserSysDTO userDto) {
        if (!this.userSysService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        UserSys user = UserSysDTO.toEntity(userDto, roleRepository, limitsRepository);
        user.setId(id);
        UserSys updatedUser = this.userSysService.save(user);
        return new ResponseEntity<>(UserSysDTO.fromEntity(updatedUser), HttpStatus.OK);
    }

    @Operation(summary = "Atualiza Parcialmente o Usuario", method = "PATCH")
    @PatchMapping("/{id}")
    public ResponseEntity<UserSysDTO> partialUpdate(@PathVariable("id") Long id, @RequestBody UserSysDTO userDto) {
        if (!this.userSysService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        UserSys updatedUser = this.userSysService.partialUpdate(id, UserSysDTO.toEntity(userDto, roleRepository, limitsRepository));
        return ResponseEntity.ok(UserSysDTO.fromEntity(updatedUser));
    }

}
