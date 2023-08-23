package br.com.unicarioca.tcc.usuariosapi.dto;

import jakarta.validation.constraints.NotNull;

public record SeguirInputDTO(@NotNull
                             Long seguidor,
                             @NotNull
                             Long seguido) {
}
