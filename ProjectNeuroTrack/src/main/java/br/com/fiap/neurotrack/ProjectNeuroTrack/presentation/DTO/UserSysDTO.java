package br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO;

import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.Limits;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.Role;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.UserSys;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.LimitsRepository;
import br.com.fiap.neurotrack.ProjectNeuroTrack.domainmodel.repositories.RoleRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Setter
@Getter
public class UserSysDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome do usuário é obrigatório.")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres.")
    private String name;

    @NotBlank(message = "E-mail é obrigatório.")
    @Email(message = "O e-mail deve ser válido.")
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    @Size(max = 100, message = "Senha deve ter no máximo 100 caracteres.")
    private String password;

    @NotBlank(message = "Status é obrigatório")
    @Size(max = 1, message = "Status deve ter no máximo 1 caractere.")
    private String status;

    private Long roleId;
    private Long limitsId;

    public static UserSysDTO fromEntity(UserSys userSys) {
        if (userSys == null) return null;
        return UserSysDTO.builder()
                .id(userSys.getId())
                .name(userSys.getName())
                .email(userSys.getEmail())
                .password(userSys.getPassword())
                .status(userSys.getStatus())
                .roleId(userSys.getRole().getId())
                .limitsId(userSys.getLimits().getId())
                .build();
    }

    public static UserSys toEntity(UserSysDTO userSysDTO, RoleRepository roleRepository, LimitsRepository limitsRepository) {
        if (userSysDTO == null) return null;

        UserSys u = new UserSys();
        u.setId(userSysDTO.getId());
        u.setName(userSysDTO.getName());
        u.setEmail(userSysDTO.getEmail());
        u.setPassword(userSysDTO.getPassword());

        if (userSysDTO.getStatus() == null || userSysDTO.getStatus().isEmpty()) {
            throw new IllegalArgumentException("Status is required");
        }
        u.setStatus(userSysDTO.getStatus());

        Role role = roleRepository.findById(userSysDTO.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found for ID: " + userSysDTO.getRoleId()));

        Limits limits = limitsRepository.findById(userSysDTO.getLimitsId())
                .orElseThrow(() -> new IllegalArgumentException("Limits not found for ID: " + userSysDTO.getLimitsId()));

        u.setRole(role);
        u.setLimits(limits);

        return u;
    }
}