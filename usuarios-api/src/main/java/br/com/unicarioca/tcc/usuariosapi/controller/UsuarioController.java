package br.com.unicarioca.tcc.usuariosapi.controller;

import br.com.unicarioca.tcc.usuariosapi.dto.UsuarioInputDTO;
import br.com.unicarioca.tcc.usuariosapi.dto.UsuarioOutputDTO;
import br.com.unicarioca.tcc.usuariosapi.service.UsuarioService;
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

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<UsuarioOutputDTO> listarTodos(@PageableDefault(size = 10) Pageable paginacao) {
        return usuarioService.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioOutputDTO> listarPorId(@PathVariable @NotNull Long id) {
        var usuarioDTO =  usuarioService.obterPorId(id);

        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping()
    public ResponseEntity<UsuarioOutputDTO> cadastrar(@RequestBody @Valid UsuarioInputDTO usuarioDTO, UriComponentsBuilder uriBuilder) {
        var usuario =  usuarioService.criar(usuarioDTO);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.id()).toUri();

        return ResponseEntity.created(uri).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioOutputDTO> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid UsuarioInputDTO usuarioDTO) {
        var usuarioAtualizado =  usuarioService.atualizar(id, usuarioDTO);

        return ResponseEntity.ok(usuarioAtualizado);
    }


}
