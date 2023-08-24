package br.com.unicarioca.tcc.usuariosapi.model;

import br.com.unicarioca.tcc.usuariosapi.dto.UsuarioInputDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @ManyToMany
    @JoinTable(name = "relacionamento_usuarios",
                joinColumns = @JoinColumn(name = "usuario_id"),
                inverseJoinColumns = @JoinColumn(name = "seguindo_id"))
    private List<Usuario> usuariosSeguidos = new ArrayList<>();

    @ManyToMany(mappedBy = "usuariosSeguidos")
    private List<Usuario> usuariosSeguidores = new ArrayList<>();

    public Usuario(String username, String encryptedPassword) {
        this.id = null;
        this.username =username;
        this.password = encryptedPassword;
    }

    public Usuario(Long id, String username, String encryptedPassword) {
        this.id = id;
        this.username =username;
        this.password = encryptedPassword;
    }

    public Usuario(UsuarioInputDTO usuarioDTO) {
        this.id = null;
        this.username = usuarioDTO.username();
        this.password = usuarioDTO.password();
    }

    public Usuario(Long id, UsuarioInputDTO usuarioDTO) {
        this.id = id;
        this.username = usuarioDTO.username();
        this.password = usuarioDTO.password();
    }

}
