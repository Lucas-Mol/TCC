package br.com.unicarioca.tcc.authserver.service;

import br.com.unicarioca.tcc.authserver.dto.LoginInputDTO;
import br.com.unicarioca.tcc.authserver.exception.CredenciaisInvalidasException;
import br.com.unicarioca.tcc.authserver.repository.ServicesRepository;
import br.com.unicarioca.tcc.authserver.service.criptografia.BcryptEncriptador;
import br.com.unicarioca.tcc.authserver.service.token.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesLogin {

    @Autowired
    ServicesRepository repository;

    @Autowired
    BcryptEncriptador encriptador;

    @Autowired
    JWTService jwtService;

    public String autentica(LoginInputDTO loginDTO) throws CredenciaisInvalidasException {
        var senha = getSenhaPorChave(loginDTO);
        validaSenha(senha, loginDTO.password());

        return gerarToken(loginDTO.username());
    }

    private String getSenhaPorChave(LoginInputDTO loginDTO) {
        return repository.getValorPorChave(loginDTO.username());
    }

    private void validaSenha(String senhaEsperada, String senha) throws CredenciaisInvalidasException {
        if(senhaEsperada == null
                || senhaEsperada.isEmpty()
                || !encriptador.valida(senha, senhaEsperada))
            throw new CredenciaisInvalidasException();
    }

    private String gerarToken(String noServico) {
        return jwtService.gerarTokenServico(noServico);
    }
}
