package br.com.unicarioca.tcc.authserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ServicesRepository {

    @Autowired
    RedisTemplate<String, String> redis;

    public String getValorPorChave(String chave) {
        return redis.opsForValue().get(chave);
    }
}
