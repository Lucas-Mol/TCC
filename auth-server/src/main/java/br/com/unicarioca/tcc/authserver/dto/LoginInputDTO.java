package br.com.unicarioca.tcc.authserver.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginInputDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
