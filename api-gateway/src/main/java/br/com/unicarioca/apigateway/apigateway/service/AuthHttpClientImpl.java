package br.com.unicarioca.apigateway.apigateway.service;

import br.com.unicarioca.apigateway.apigateway.dto.LoginDTO;
import br.com.unicarioca.apigateway.apigateway.dto.TokenDTO;
import br.com.unicarioca.apigateway.apigateway.dto.ValidaTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuthHttpClientImpl implements AuthClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public ValidaTokenDTO validaToken(TokenDTO tokenDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity requestEntity = new HttpEntity<>(tokenDTO);

        String apiUri = getEnderecoServicoAuthNoEureka() + "/token";

        return restTemplate.exchange(apiUri, HttpMethod.POST, requestEntity, ValidaTokenDTO.class).getBody();
    }

    @Override
    public TokenDTO autenticaServico(LoginDTO loginDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity requestEntity = new HttpEntity<>(loginDTO);

        String apiUri = getEnderecoServicoAuthNoEureka() + "/service";

        return restTemplate.exchange(apiUri, HttpMethod.POST, requestEntity, TokenDTO.class).getBody();

    }

    private String getEnderecoServicoAuthNoEureka() {
        List<ServiceInstance> instances = discoveryClient.getInstances("auth-server");

        if (instances != null && !instances.isEmpty()) {
            ServiceInstance serviceInstance = instances.get(0);
            return serviceInstance.getUri().toString();
        } else {
            throw new RuntimeException("Erro ao se comunicar com o serviço de autenticação e autorização!");
        }
    }
}
