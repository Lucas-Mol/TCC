package br.com.unicarioca.tcc.usuariosapi.service;

import br.com.unicarioca.tcc.usuariosapi.dto.UsuarioInputDTO;
import br.com.unicarioca.tcc.usuariosapi.dto.UsuarioOutputDTO;
import br.com.unicarioca.tcc.usuariosapi.model.Usuario;
import br.com.unicarioca.tcc.usuariosapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioOutputDTO> obterTodos(Pageable paginacao) {
        return usuarioRepository
                .findAll(paginacao)
                .map(UsuarioOutputDTO::new);
    }

    public UsuarioOutputDTO obterPorId(Long id) {
        var usuario = usuarioRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new UsuarioOutputDTO(usuario);
    }

    public UsuarioOutputDTO criar(UsuarioInputDTO usuarioDTO) {
        var usuario = usuarioRepository.save(new Usuario(usuarioDTO));

        return new UsuarioOutputDTO(usuario);
    }

    public UsuarioOutputDTO atualizar(Long id, UsuarioInputDTO usuarioDTO) {
        var usuario = new Usuario(id, usuarioDTO);
        var usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioOutputDTO(usuarioAtualizado);
    }

}
