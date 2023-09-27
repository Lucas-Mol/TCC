package br.com.unicarioca.tcc.postagensapi.dto;

import jakarta.validation.constraints.NotBlank;

public record PostagemEdicaoDTO(

        @NotBlank
        String usuario,
        @NotBlank
        String texto
) {
}
