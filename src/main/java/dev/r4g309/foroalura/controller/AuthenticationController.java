package dev.r4g309.foroalura.controller;


import dev.r4g309.foroalura.domain.user.models.User;
import dev.r4g309.foroalura.domain.user.models.dto.AuthUser;
import dev.r4g309.foroalura.infra.security.TokenData;
import dev.r4g309.foroalura.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticacion", description = "obtiene el token para el usuario asignado que da acceso al resto de endpoint")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authUser(@RequestBody @Valid AuthUser authUser) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(authUser.email(),
                authUser.password());

        Authentication validatedUser = authenticationManager.authenticate(authToken);
        String JWTToken = tokenService.generateToken((User) validatedUser.getPrincipal());

        return ResponseEntity.ok(new TokenData(JWTToken));
    }


}
