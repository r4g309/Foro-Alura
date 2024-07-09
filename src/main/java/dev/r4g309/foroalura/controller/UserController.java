package dev.r4g309.foroalura.controller;

import dev.r4g309.foroalura.domain.user.UserRepository;
import dev.r4g309.foroalura.domain.user.models.User;
import dev.r4g309.foroalura.domain.user.models.dto.NewUser;
import dev.r4g309.foroalura.domain.user.models.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@ResponseBody
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    @Operation(summary = "Get the list of users")
    public ResponseEntity<Page<UserResponse>> listUsers(Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(pageable)
                .map(UserResponse::new));
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Register a new user in the database")
    public ResponseEntity<UserResponse> registerNewUser(@RequestBody @Valid NewUser newUser, UriComponentsBuilder uriComponentsBuilder) {

        User user = userRepository.save(new User(newUser));

        URI url = uriComponentsBuilder.path("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(url)
                .body(new UserResponse(user));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow();
        return ResponseEntity.ok(new UserResponse(user));
    }
}
