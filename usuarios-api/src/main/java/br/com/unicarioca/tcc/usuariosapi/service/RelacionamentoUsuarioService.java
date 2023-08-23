package br.com.unicarioca.tcc.usuariosapi.service;

import br.com.unicarioca.tcc.usuariosapi.dto.SeguirInputDTO;
import br.com.unicarioca.tcc.usuariosapi.dto.UsuarioOutputDTO;
import br.com.unicarioca.tcc.usuariosapi.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelacionamentoUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioOutputDTO> obterUsuariosSeguidos(Long id, Pageable paginacao) {
        return usuarioRepository
                .findSeguidosByUserId(id, paginacao)
                .map(UsuarioOutputDTO::new);
    }

    public List<UsuarioOutputDTO> obterTodosUsuariosSeguidos(Long id) {
        return usuarioRepository
                .findTodosSeguidosByUserId(id)
                .stream()
                .map(UsuarioOutputDTO::new)
                .collect(Collectors.toList());
    }

    public Page<UsuarioOutputDTO> obterUsuariosSeguidores(Long id, Pageable paginacao) {
        return usuarioRepository
                .findSeguidoresByUserId(id, paginacao)
                .map(UsuarioOutputDTO::new);
    }

    public void seguir(SeguirInputDTO seguirDTO) {
        this.seguir(seguirDTO.seguidor(), seguirDTO.seguido());
    }

    public void seguir(Long idSeguidor, Long idSeguido) {
        var seguidor = usuarioRepository
                .findById(idSeguidor)
                .orElseThrow(EntityNotFoundException::new);

        var seguido = usuarioRepository
                .findById(idSeguido)
                .orElseThrow(EntityNotFoundException::new);

        seguidor.getUsuariosSeguidos().add(seguido);

        usuarioRepository.save(seguidor);
    }

    public void deixarSeguir(SeguirInputDTO seguirDTO) {
        this.deixarSeguir(seguirDTO.seguidor(), seguirDTO.seguido());
    }

    public void deixarSeguir(Long idSeguidor, Long idSeguido) {
        var seguidor = usuarioRepository
                .findById(idSeguidor)
                .orElseThrow(EntityNotFoundException::new);

        var seguido = usuarioRepository
                .findById(idSeguido)
                .orElseThrow(EntityNotFoundException::new);

        seguidor.getUsuariosSeguidos().remove(seguido);

        usuarioRepository.save(seguidor);
    }

}
