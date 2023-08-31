package br.com.unicarioca.tcc.usuariosapi.security;

import br.com.unicarioca.tcc.usuariosapi.dto.TokenDTO;
import br.com.unicarioca.tcc.usuariosapi.http.AuthClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;


@Component
public class ServiceFilter extends OncePerRequestFilter {

    @Autowired
    AuthClient authClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader("service-authentication");

        if(token != null && !token.isEmpty()) {
            var responseValidaToken = authClient.validaToken(new TokenDTO(token));

            if(responseValidaToken.valido()){
                filterChain.doFilter(request, response);
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}

