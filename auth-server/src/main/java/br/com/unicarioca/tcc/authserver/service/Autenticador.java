package br.com.unicarioca.tcc.authserver.service;

import br.com.unicarioca.tcc.authserver.dto.LoginInputDTO;
import br.com.unicarioca.tcc.authserver.exception.CredenciaisInvalidasException;

public interface Autenticador {

    String autentica(LoginInputDTO loginDTO) throws CredenciaisInvalidasException;
}
