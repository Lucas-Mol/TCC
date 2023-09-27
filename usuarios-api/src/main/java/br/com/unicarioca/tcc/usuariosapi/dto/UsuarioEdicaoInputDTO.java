package br.com.unicarioca.tcc.usuariosapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioEdicaoInputDTO(
        @NotNull
        Long id,
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
