package br.com.unicarioca.tcc.usuariosapi.service.criptografia;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BcryptEncriptador implements Encriptador {

    @Override
    public String encriptar(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }
}
