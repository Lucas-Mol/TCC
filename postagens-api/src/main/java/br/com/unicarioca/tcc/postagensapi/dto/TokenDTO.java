package br.com.unicarioca.tcc.postagensapi.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        @NotBlank
        String token
) {
}
