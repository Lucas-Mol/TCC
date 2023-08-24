package br.com.unicarioca.tcc.authserver.service.token;

import br.com.unicarioca.tcc.authserver.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTService implements TokenService {

    @Value("${env.var.jwt-secret}")
    private String secret;

    private final String JWT_ISSUER = "Auth Server TCC";

    @Override
    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withSubject(String.valueOf(usuario.getId()))
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    @Override
    public boolean validaToken(String tokenJWT) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            var decodedJWT = JWT.require(algoritmo)
                    .withIssuer(JWT_ISSUER)
                    .build()
                    .verify(tokenJWT);
            return decodedJWT != null;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
    }
}
