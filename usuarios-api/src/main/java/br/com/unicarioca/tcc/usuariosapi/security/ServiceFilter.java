package br.com.unicarioca.tcc.usuariosapi.security;

import br.com.unicarioca.tcc.usuariosapi.dto.TokenDTO;
import br.com.unicarioca.tcc.usuariosapi.http.AuthClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;


@Component
@Order(2)
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
                return;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}

