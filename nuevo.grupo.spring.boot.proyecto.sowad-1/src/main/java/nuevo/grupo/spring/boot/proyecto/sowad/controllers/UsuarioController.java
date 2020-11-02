package nuevo.grupo.spring.boot.proyecto.sowad.controllers;

import lombok.RequiredArgsConstructor;
import nuevo.grupo.spring.boot.proyecto.sowad.models.entity.Usuario;
import nuevo.grupo.spring.boot.proyecto.sowad.services.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins= "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/QW")
public class UsuarioController {
    private final UsuarioServiceImpl userService;

    @GetMapping("/r")
    public String login() {
        return "authenticated successfully";
    }

    @GetMapping("/usuario/{username}")
    public Usuario getByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}
