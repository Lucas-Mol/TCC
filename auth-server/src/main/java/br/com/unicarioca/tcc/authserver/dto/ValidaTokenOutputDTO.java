package br.com.unicarioca.tcc.authserver.dto;

import jakarta.validation.constraints.NotNull;

public record ValidaTokenOutputDTO(
        @NotNull
        boolean valido
) {
}
