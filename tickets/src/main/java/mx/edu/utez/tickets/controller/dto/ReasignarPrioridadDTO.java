package mx.edu.utez.tickets.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.tickets.model.PrioridadTicket;

@Getter
@Setter
public class ReasignarPrioridadDTO {

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;
}