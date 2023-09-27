package br.com.unicarioca.tcc.authserver.service.token;

import br.com.unicarioca.tcc.authserver.model.Usuario;

public interface TokenService {
    public String gerarTokenUsuario(Usuario usuario);
    public boolean validaToken(String token);
}
