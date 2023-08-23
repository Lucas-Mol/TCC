package br.com.unicarioca.tcc.usuariosapi.repository;

import br.com.unicarioca.tcc.usuariosapi.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT s FROM Usuario u JOIN u.usuariosSeguidos s WHERE u.id = :userId")
    Page<Usuario> findSeguidosByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT s FROM Usuario u JOIN u.usuariosSeguidos s WHERE u.id = :userId")
    List<Usuario> findTodosSeguidosByUserId(@Param("userId") Long userId);

    @Query("SELECT s FROM Usuario u JOIN u.usuariosSeguidores s WHERE u.id = :userId")
    Page<Usuario> findSeguidoresByUserId(@Param("userId") Long userId, Pageable pageable);
}
