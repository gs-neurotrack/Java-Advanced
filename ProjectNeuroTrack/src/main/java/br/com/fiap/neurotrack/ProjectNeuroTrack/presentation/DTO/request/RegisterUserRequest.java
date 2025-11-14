package br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.request;

import javax.validation.constraints.NotEmpty;

public record RegisterUserRequest(@NotEmpty(message = "Nome é obrigatório.") String name,
                                  @NotEmpty(message = "Email é obrigatório.") String email,
                                  @NotEmpty(message = "Senha é obrigatório.") String password,
                                  @NotEmpty(message = "Status é obrigatório.") String status,
                                  @NotEmpty(message = "ID Role é obrigatório.") Long roleId,
                                  @NotEmpty(message = "ID Limit é obrigatório.") Long limitsId) {
}
