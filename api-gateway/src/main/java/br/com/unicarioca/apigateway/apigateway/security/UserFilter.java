package br.com.unicarioca.apigateway.apigateway.security;

import br.com.unicarioca.apigateway.apigateway.dto.TokenDTO;
import br.com.unicarioca.apigateway.apigateway.dto.ValidaTokenDTO;
import br.com.unicarioca.apigateway.apigateway.exception.PathPermitidoException;
import br.com.unicarioca.apigateway.apigateway.exception.TokenValidoException;
import br.com.unicarioca.apigateway.apigateway.service.AuthHttpClientImpl;
import org.apache.catalina.core.AprLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserFilter implements WebFilter {

    @Autowired
    private AuthHttpClientImpl auth;

    @Value("${env.var.allowed_paths}")
    private String ALLOWED_PATHS;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            liberaPathsPermitidos(exchange);

            verificaValidadeToken(exchange);

            var response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        } catch (TokenValidoException | PathPermitidoException ex) {
            return chain.filter(exchange);
        }
    }

    private void liberaPathsPermitidos(ServerWebExchange exchange) throws PathPermitidoException {
        var requestPath = exchange.getRequest().getPath().value();
        var requestMethod = exchange.getRequest().getMethod().name();

        var pathsPermitidos = List.of(ALLOWED_PATHS.split("\\|\\|"));

        if(requestMethod.equals("POST")
                && pathsPermitidos.contains(requestPath)) {
            throw new PathPermitidoException();
        }
    }

    private void verificaValidadeToken(ServerWebExchange exchange) throws TokenValidoException {
        var token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if(token != null && !token.isEmpty()) {
            var response = auth.validaToken(new TokenDTO(trataTokenHeader(token)));

            if(response.valido()) {
                throw new TokenValidoException();
            }
        }
    }

    private String trataTokenHeader(String token) {
        return token.replace("Bearer ", "");
    }
}
