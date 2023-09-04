package br.com.unicarioca.tcc.postagensapi.dto;

import br.com.unicarioca.tcc.postagensapi.model.Postagem;
import jakarta.validation.constraints.NotBlank;

public record PostagemDTO(
        String id,
        @NotBlank
        String texto
) {
    public PostagemDTO(Postagem postagem) {
        this(postagem.getId(), postagem.getTexto());
    }
}
