package br.com.unicarioca.tcc.postagensapi.controller;

import br.com.unicarioca.tcc.postagensapi.dto.PostagemDTO;
import br.com.unicarioca.tcc.postagensapi.dto.PostagemEdicaoDTO;
import br.com.unicarioca.tcc.postagensapi.dto.PostagemListagemUsuariosDTO;
import br.com.unicarioca.tcc.postagensapi.dto.PostagemRemocaoDTO;
import br.com.unicarioca.tcc.postagensapi.repository.PostagemRepository;
import br.com.unicarioca.tcc.postagensapi.service.PostagemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/postagens")
@Validated
public class PostagemController {

    @Autowired
    private PostagemService service;

    @GetMapping
    public Page<PostagemDTO> listarTodos(@PageableDefault(size = 10) Pageable paginacao) {
        return service.obterTodas(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostagemDTO> listarPorId(@PathVariable @NotBlank String id) {
        var postagemDTO =  service.obterPostagemPorId(id);

        return ResponseEntity.ok(postagemDTO);
    }

    @GetMapping("/seguidos/usuarios/{usuario}")
    public Page<PostagemDTO> listarPorSeguidosDoUsuarioId(@PathVariable @NotBlank String usuario,
                                               @PageableDefault(size = 10) Pageable paginacao) {
        return service.obterPaginacaoPostagensPorSeguidosDoUsuario(usuario, paginacao);
    }

    @GetMapping("/usuarios")
    public Page<PostagemDTO> listarPorUsuarios(@RequestBody @Valid PostagemListagemUsuariosDTO usuariosDTO,
                                              @PageableDefault(size = 10) Pageable paginacao) {
        return service.obterPaginacaoPostagensPorListagemUsuarios(usuariosDTO.usuarios(), paginacao);
    }

    @GetMapping("/usuarios/{usuario}")
    public Page<PostagemDTO> listarPorUsuarioId(@PathVariable @NotBlank String usuario,
                                         @PageableDefault(size = 10) Pageable paginacao) {
        return service.obterPaginacaoPostagensPorUsuario(usuario, paginacao);
    }

    @PostMapping
    public ResponseEntity<PostagemDTO> postar(@RequestBody @Valid PostagemDTO postagemDTO, UriComponentsBuilder uriBuilder) {
        var postagem =  service.postar(postagemDTO);
        var uri = uriBuilder.path("/postagens/{id}").buildAndExpand(postagem.id()).toUri();

        return ResponseEntity.created(uri).body(postagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostagemDTO> editar(@PathVariable @NotBlank String id, @RequestBody @Valid PostagemEdicaoDTO postagemDTO) {
        var postagemEditada =  service.editar(id, postagemDTO);

        return ResponseEntity.ok(postagemEditada);
    }

    @DeleteMapping
    public ResponseEntity remover(@RequestBody @Valid PostagemRemocaoDTO postagemDTO) {
        service.remover(postagemDTO.id());

        return ResponseEntity.noContent().build();
    }
}
