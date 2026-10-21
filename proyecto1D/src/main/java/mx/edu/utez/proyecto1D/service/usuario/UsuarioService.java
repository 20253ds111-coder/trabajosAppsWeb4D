package mx.edu.utez.proyecto1D.service.usuario;

import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestCreateUserDTO;
import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestUpdateUsuarioDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1D.model.persona.Persona;
import mx.edu.utez.proyecto1D.model.usuario.Roles;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto1D.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    @Transactional(readOnly = true)
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("El usuario con ID " + id + " no existe"));
    }

    @Transactional
    public Usuario createUser(RequestCreateUserDTO payload) {
        if (usuarioRepository.findByUsername(payload.getUsername()).isPresent()) {
            throw new CustomBadRequestException("El username ya esta en uso");
        }

        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(payload.getNombres());
        nuevaPersona.setPrimerApellido(payload.getPrimerApellido());
        nuevaPersona.setSegundoApellido(payload.getSegundoApellido());
        nuevaPersona.setCorreo(payload.getCorreo());
        nuevaPersona.setCurp(payload.getCurp());
        nuevaPersona.setFechaNacimiento(payload.getFechaNacimiento());

        nuevaPersona = personaRepository.save(nuevaPersona);

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(payload.getUsername());
        nuevoUsuario.setPassword(payload.getPassword());
        nuevoUsuario.setDescripcion(payload.getDescripcion());
        nuevoUsuario.setEnable(true);
        nuevoUsuario.setRol(Roles.valueOf(payload.getRol()));
        nuevoUsuario.setPersona(nuevaPersona);

        return usuarioRepository.save(nuevoUsuario);
    }

    @Transactional
    public Usuario cambiarEstatus(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("El usuario con ID " + id + " no existe"));
        usuario.setEnable(!usuario.isEnable());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario actualizarUsuario(Long id, RequestUpdateUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("El usuario con ID " + id + " no existe"));

        usuario.setUsername(dto.getUsername());
        usuario.setDescripcion(dto.getDescripcion());

        return usuarioRepository.save(usuario);
    }
}