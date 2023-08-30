package br.com.unicarioca.apigateway.apigateway.service;

import br.com.unicarioca.apigateway.apigateway.dto.LoginDTO;
import br.com.unicarioca.apigateway.apigateway.dto.TokenDTO;
import br.com.unicarioca.apigateway.apigateway.dto.ValidaTokenDTO;

public interface AuthClient {

    ValidaTokenDTO validaToken(TokenDTO tokenDTO);

    TokenDTO autenticaServico(LoginDTO loginDTO);
}
