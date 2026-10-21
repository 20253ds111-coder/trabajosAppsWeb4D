package mx.edu.utez.proyecto1D.controller.persona;


import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.persona.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1D.model.persona.Persona;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.service.persona.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/persona")
public class PersonaController {


    private final PersonaService personaService;


    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getAllPerson(){
        return ResponseEntity.status(200).body(personaService.getAllPerson());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }


    @PostMapping("/actualizar/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona payload) {
        return ResponseEntity.status(200).body(
                personaService.updatePersona(id, payload)
        );
    }
}