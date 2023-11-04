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
@Order(1)
public class UserFilter extends OncePerRequestFilter {

    @Autowired
    private AuthClient auth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader("Authorization");

        if(token != null && !token.isEmpty()) {
            var responseValidaToken = auth.validaToken(new TokenDTO(trataTokenHeader(token)));

            if(responseValidaToken.valido()){
                filterChain.doFilter(request, response);
                return;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    private String trataTokenHeader(String token) {
        return token.replace("Bearer ", "");
    }
}
