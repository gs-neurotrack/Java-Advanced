package br.com.fiap.neurotrack.ProjectNeuroTrack.presentation.DTO.request;

import javax.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty(message = "Email é obrigatório.") String email,
                           @NotEmpty(message = "Senha é obrigatória.") String password) {
}
