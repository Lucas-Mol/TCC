package br.com.unicarioca.tcc.postagensapi.http;

import br.com.unicarioca.tcc.postagensapi.dto.UsuarioInputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("usuarios-api")
public interface UsuarioClient {
    @RequestMapping(method = RequestMethod.GET, value = "/usuarios/relacionamentos/seguidos/todos/{id}")
    List<UsuarioInputDTO> getSeguidosDoUsuario(@PathVariable Long id);
}
