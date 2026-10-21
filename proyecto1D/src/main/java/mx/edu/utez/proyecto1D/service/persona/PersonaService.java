package mx.edu.utez.proyecto1D.service.persona;

import mx.edu.utez.proyecto1D.controller.persona.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1D.model.persona.Persona;
import mx.edu.utez.proyecto1D.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> getAllPerson(){
        return personaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Persona obtenerPorId(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("La persona con ID " + id + " no existe"));
    }

    @Transactional
    public Persona updatePersona(Long id, Persona payload) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        persona.setNombre(payload.getNombre());
        persona.setPrimerApellido(payload.getPrimerApellido());
        persona.setSegundoApellido(payload.getSegundoApellido());
        persona.setCorreo(payload.getCorreo());
        persona.setCurp(payload.getCurp());
        persona.setFechaNacimiento(payload.getFechaNacimiento());
        return personaRepository.save(persona);
    }
}