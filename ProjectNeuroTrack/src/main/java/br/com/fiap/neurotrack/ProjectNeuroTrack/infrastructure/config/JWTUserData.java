package br.com.fiap.neurotrack.ProjectNeuroTrack.infrastructure.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}
