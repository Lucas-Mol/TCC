package br.com.unicarioca.tcc.postagensapi.model;

import br.com.unicarioca.tcc.postagensapi.dto.PostagemDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("postagens")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Postagem {

    @Id
    private String id;
    private String usuario;
    private String texto;

    public Postagem(PostagemDTO dto) {
        this.usuario = dto.usuario();
        this.texto = dto.texto();
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
