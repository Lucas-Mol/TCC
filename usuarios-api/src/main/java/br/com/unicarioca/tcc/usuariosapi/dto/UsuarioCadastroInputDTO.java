package br.com.unicarioca.tcc.usuariosapi.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioCadastroInputDTO(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
