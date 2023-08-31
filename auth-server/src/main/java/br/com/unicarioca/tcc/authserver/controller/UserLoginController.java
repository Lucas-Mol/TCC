package br.com.unicarioca.tcc.authserver.controller;

import br.com.unicarioca.tcc.authserver.dto.ErroDTO;
import br.com.unicarioca.tcc.authserver.dto.LoginInputDTO;
import br.com.unicarioca.tcc.authserver.dto.TokenDTO;
import br.com.unicarioca.tcc.authserver.exception.CredenciaisInvalidasException;
import br.com.unicarioca.tcc.authserver.service.UsersLogin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Validated
public class UserLoginController {

    @Autowired
    UsersLogin service;

    @PostMapping
    public ResponseEntity logar(@RequestBody @Valid LoginInputDTO loginDTO) {
        try {
            return ResponseEntity.ok(new TokenDTO(service.autentica(loginDTO)));
        } catch (CredenciaisInvalidasException e) {
            return ResponseEntity.ok(new ErroDTO("Credenciais inválidas"));
        }
    }

}
