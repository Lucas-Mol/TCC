package br.com.unicarioca.tcc.usuariosapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class ServiceFilter implements WebFilter {

    @Value("${env.var.service-name}")
    String serviceName;

    @Value("${env.var.service-password}")
    String servicePassword;

    @Autowired
    AuthHttpClientImpl authClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var response = authClient.autenticaServico(new LoginDTO(serviceName, servicePassword));

        if(response != null && response.token() != null && !response.token().isEmpty()){
            exchange.getRequest().getHeaders().set("Service Authentication", response.token());
        }

        return chain.filter(exchange);
    }

}

