package br.com.unicarioca.tcc.postagensapi.http;

import br.com.unicarioca.tcc.postagensapi.dto.TokenDTO;
import br.com.unicarioca.tcc.postagensapi.dto.ValidaTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("auth-server")
public interface AuthClient {
    @RequestMapping(method = RequestMethod.POST, value = "/token")
    ValidaTokenDTO validaToken(@RequestBody TokenDTO tokenDTO);
}
