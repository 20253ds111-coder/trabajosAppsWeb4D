package mx.edu.utez.proyecto1D.controller.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateUsuarioDTO {

    @NotBlank(message = "El username no puede estar vacío")
    private String username;

    private String descripcion;
}
