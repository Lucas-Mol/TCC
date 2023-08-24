package br.com.unicarioca.tcc.authserver.service.criptografia;

public interface Encriptador {
    String encripta(String senha);
    boolean valida(String senha, String senhaEncriptada);
}
