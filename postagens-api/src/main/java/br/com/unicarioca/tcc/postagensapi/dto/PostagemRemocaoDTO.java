package br.com.unicarioca.tcc.postagensapi.dto;

import jakarta.validation.constraints.NotBlank;

public record PostagemRemocaoDTO(

        @NotBlank
        String id,
        @NotBlank
        String usuario
) {
}
