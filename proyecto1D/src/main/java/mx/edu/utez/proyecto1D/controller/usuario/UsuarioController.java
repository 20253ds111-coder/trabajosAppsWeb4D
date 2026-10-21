package mx.edu.utez.proyecto1D.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestCreateUserDTO;
import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestUpdateUsuarioDTO;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity.status(200).body(
                usuarioService.getAllUsuarios()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(
                usuarioService.obtenerPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody @Valid RequestCreateUserDTO payload) {
        return ResponseEntity.status(201).body(
                usuarioService.createUser(payload)
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Usuario> cambiarEstatus(@PathVariable Long id) {
        return ResponseEntity.status(200).body(
                usuarioService.cambiarEstatus(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody RequestUpdateUsuarioDTO dto) {
        return ResponseEntity.status(200).body(
                usuarioService.actualizarUsuario(id, dto)
        );
    }
}
