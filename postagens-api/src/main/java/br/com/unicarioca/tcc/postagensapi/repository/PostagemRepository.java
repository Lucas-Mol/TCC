package br.com.unicarioca.tcc.postagensapi.repository;

import br.com.unicarioca.tcc.postagensapi.model.Postagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostagemRepository extends MongoRepository<Postagem, String> {

    public Page<Postagem> findPostagemsByUsuario(String usuario, Pageable paginacao);

    @Query("{ 'usuario': { $in: ?0 } }")
    public Page<Postagem> findPostagemsByListagemUsuarios(List<String> usuarios, Pageable paginacao);

}
