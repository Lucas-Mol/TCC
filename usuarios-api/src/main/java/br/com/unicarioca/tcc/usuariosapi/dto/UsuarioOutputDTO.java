package br.com.unicarioca.tcc.usuariosapi.dto;

import br.com.unicarioca.tcc.usuariosapi.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioOutputDTO(
        @NotNull
        Long id,
        @NotBlank
        String username
) {
        public UsuarioOutputDTO(Usuario usuario) {
                this(usuario.getId(), usuario.getUsername());
        }
}
