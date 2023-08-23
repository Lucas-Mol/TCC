package br.com.unicarioca.tcc.postagensapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioInputDTO(
        @NotNull
        Long id,
        @NotBlank
        String username
) {
}
