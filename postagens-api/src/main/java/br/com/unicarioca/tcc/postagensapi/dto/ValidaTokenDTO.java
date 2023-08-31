package br.com.unicarioca.tcc.postagensapi.dto;

import jakarta.validation.constraints.NotNull;

public record ValidaTokenDTO(
        @NotNull
        boolean valido
) {
}
