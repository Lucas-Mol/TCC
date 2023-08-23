package br.com.unicarioca.tcc.usuariosapi.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioInputDTO (
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
