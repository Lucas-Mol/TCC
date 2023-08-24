package br.com.unicarioca.tcc.postagensapi.service;

import br.com.unicarioca.tcc.postagensapi.dto.PostagemDTO;
import br.com.unicarioca.tcc.postagensapi.dto.PostagemEdicaoDTO;
import br.com.unicarioca.tcc.postagensapi.http.UsuarioClient;
import br.com.unicarioca.tcc.postagensapi.model.Postagem;
import br.com.unicarioca.tcc.postagensapi.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PostagemService {

    @Autowired
    PostagemRepository repository;

    @Autowired
    UsuarioClient usuarioClient;

    public PostagemDTO postar(PostagemDTO postagemDTO) {
        var postagem = repository.save(new Postagem(postagemDTO));

        return new PostagemDTO(postagem);
    }

    public PostagemDTO editar(String id, PostagemEdicaoDTO postagemDTO) {
        return this.editar(id, postagemDTO.texto());
    }

    public PostagemDTO editar(String id, String texto) {
        var postagem = repository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        postagem.setTexto(texto);

        var postagemEditada = repository.save(postagem);

        return new PostagemDTO(postagemEditada);
    }

    public void remover(String id) {
        repository.deleteById(id);
    }

    public PostagemDTO obterPostagemPorId(String id) {
        var postagem = repository
                .findById(id)
                .orElseThrow(RuntimeException::new);

        return new PostagemDTO(postagem);
    }

    public Page<PostagemDTO> obterPaginacaoPostagensPorUsuario(String usuario, Pageable paginacao) {
        return repository
                .findPostagemsByUsuario(usuario, paginacao)
                .map(PostagemDTO::new);
    }

    public Page<PostagemDTO> obterPaginacaoPostagensPorSeguidosDoUsuario(String usuario, Pageable paginacao) {
        var usuarios = usuarioClient.getSeguidosDoUsuario(Long.parseLong(usuario));

        var usuariosId = usuarios.stream()
                .map(usuarioInputDTO -> String.valueOf(usuarioInputDTO.id()))
                .collect(Collectors.toList());

        return repository
                .findPostagemsByListagemUsuarios(usuariosId, paginacao)
                .map(PostagemDTO::new);
    }

    public Page<PostagemDTO> obterPaginacaoPostagensPorListagemUsuarios(List<String> usuarios, Pageable paginacao) {
        return repository
                .findPostagemsByListagemUsuarios(usuarios, paginacao)
                .map(PostagemDTO::new);
    }

    public Page<PostagemDTO> obterTodas(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(PostagemDTO::new);
    }
}
