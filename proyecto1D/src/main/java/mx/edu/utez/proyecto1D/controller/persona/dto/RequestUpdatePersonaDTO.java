package mx.edu.utez.proyecto1D.controller.persona.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdatePersonaDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El primer apellido no puede estar vacío")
    private String primerApellido;

    private String segundoApellido;
    private String curp;
}