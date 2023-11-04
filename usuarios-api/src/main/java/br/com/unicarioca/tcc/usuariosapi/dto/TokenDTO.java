package br.com.unicarioca.tcc.usuariosapi.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        @NotBlank
        String token
) {
}
