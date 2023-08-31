package br.com.unicarioca.tcc.authserver.controller;

import br.com.unicarioca.tcc.authserver.dto.ValidaTokenOutputDTO;
import br.com.unicarioca.tcc.authserver.dto.TokenDTO;
import br.com.unicarioca.tcc.authserver.service.token.JWTService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@Validated
public class TokenController {

    @Autowired
    JWTService tokenService;

    @PostMapping
    public ResponseEntity validaTokenUsuario(@RequestBody @Valid TokenDTO tokenDTO) {
        return ResponseEntity.ok(new ValidaTokenOutputDTO(tokenService.validaToken(tokenDTO.token())));
    }

}
