package br.com.unicarioca.tcc.usuariosapi.controller;

import br.com.unicarioca.tcc.usuariosapi.dto.SeguirInputDTO;
import br.com.unicarioca.tcc.usuariosapi.dto.UsuarioOutputDTO;
import br.com.unicarioca.tcc.usuariosapi.service.RelacionamentoUsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios/relacionamentos")
@Validated
public class RelacionamentoUsuarioController {

    @Autowired
    private RelacionamentoUsuarioService service;

    @GetMapping("/seguidos/{id}")
    public Page<UsuarioOutputDTO> listarUsuariosSeguidos(@PathVariable @NotNull Long id,
                                                         @PageableDefault(size = 10) Pageable paginacao) {
        return service.obterUsuariosSeguidos(id, paginacao);
    }

    @GetMapping("/seguidos/todos/{id}")
    public List<UsuarioOutputDTO> listarTodosUsuariosSeguidos(@PathVariable @NotNull Long id) {
        return service.obterTodosUsuariosSeguidos(id);
    }

    @GetMapping("/seguidores/{id}")
    public Page<UsuarioOutputDTO> listarUsuariosSeguidores(@PathVariable @NotNull Long id,
                                                         @PageableDefault(size = 10) Pageable paginacao) {
        return service.obterUsuariosSeguidores(id, paginacao);
    }

    @PostMapping()
    public ResponseEntity seguir(@RequestBody @Valid SeguirInputDTO seguirDTO, UriComponentsBuilder uriBuilder) {
        service.seguir(seguirDTO);
        var uri = uriBuilder.path("/usuarios/relacionamentos/seguidos{id}").buildAndExpand(seguirDTO.seguidor()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping
    public ResponseEntity deixarSeguir(@RequestBody @Valid SeguirInputDTO seguirDTO, UriComponentsBuilder uriBuilder) {
        service.deixarSeguir(seguirDTO);
        var uri = uriBuilder.path("/usuarios/relacionamentos/seguidos{id}").buildAndExpand(seguirDTO.seguidor()).toUri();

        return ResponseEntity.created(uri).build();
    }


}
