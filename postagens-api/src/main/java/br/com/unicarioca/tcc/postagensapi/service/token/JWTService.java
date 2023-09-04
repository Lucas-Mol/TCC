package br.com.unicarioca.tcc.postagensapi.service.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JWTService implements TokenService {

    @Override
    public String getUsuario(String token) {
        var base64Payload = token.split("\\.")[1];
        var payload = new String(Base64.getDecoder().decode(base64Payload));

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(payload);

           return jsonNode.get("sub").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
