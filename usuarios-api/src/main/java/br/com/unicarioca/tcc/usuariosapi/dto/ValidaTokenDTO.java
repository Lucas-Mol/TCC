package br.com.unicarioca.tcc.usuariosapi.dto;

import jakarta.validation.constraints.NotNull;

public record ValidaTokenDTO(
        @NotNull
        boolean valido
) {
}
