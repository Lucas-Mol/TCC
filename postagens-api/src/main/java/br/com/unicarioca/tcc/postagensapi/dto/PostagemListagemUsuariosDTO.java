package br.com.unicarioca.tcc.postagensapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PostagemListagemUsuariosDTO(
        @NotNull
        @NotEmpty
        List<String> usuarios
) {
}
