package br.com.unicarioca.tcc.authserver.service.criptografia;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BcryptEncriptador implements Encriptador{
    @Override
    public String encripta(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    @Override
    public boolean valida(String senha, String senhaEncriptada) {
        return BCrypt.checkpw(senha, senhaEncriptada);
    }
}
