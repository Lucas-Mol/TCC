package br.com.unicarioca.apigateway.apigateway.dto;

import jakarta.validation.constraints.NotNull;

public record ValidaTokenDTO(
        @NotNull
        boolean valido
) {
}
