package mx.edu.utez.tickets.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.tickets.model.PrioridadTicket;

@Getter
@Setter
public class CrearTicketDTO {

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;

    @NotNull(message = "El id de usuario es obligatorio")
    private Long usuarioId;
}