package br.com.unicarioca.tcc.authserver.repository;

import br.com.unicarioca.tcc.authserver.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findUsuarioByUsername(String username);
}
