package br.com.unicarioca.tcc.authserver.service;

import br.com.unicarioca.tcc.authserver.dto.LoginInputDTO;
import br.com.unicarioca.tcc.authserver.exception.CredenciaisInvalidasException;
import br.com.unicarioca.tcc.authserver.model.Usuario;
import br.com.unicarioca.tcc.authserver.repository.UsuarioRepository;
import br.com.unicarioca.tcc.authserver.service.criptografia.BcryptEncriptador;
import br.com.unicarioca.tcc.authserver.service.token.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersLogin implements Autenticador {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    BcryptEncriptador encriptador;
    @Autowired
    JWTService jwtService;

    @Override
    public String autentica(LoginInputDTO loginDTO) throws CredenciaisInvalidasException {
        var usuario = getUsuario(loginDTO);
        validaSenhaUsuario(loginDTO, usuario);

        return gerarToken(usuario);
    }

    private Usuario getUsuario(LoginInputDTO loginDTO) throws CredenciaisInvalidasException {
        return repository
                .findUsuarioByUsername(loginDTO.username())
                .orElseThrow(CredenciaisInvalidasException::new);

    }

    private void validaSenhaUsuario(LoginInputDTO loginDTO, Usuario usuario) throws CredenciaisInvalidasException {
        if(!encriptador.valida(loginDTO.password(), usuario.getPassword())) {
            throw new CredenciaisInvalidasException();
        }
    }

    private String gerarToken(Usuario usuario) {
        return jwtService.gerarTokenUsuario(usuario);
    }
}
