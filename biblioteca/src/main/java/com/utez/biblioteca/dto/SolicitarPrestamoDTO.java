package com.utez.biblioteca.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitarPrestamoDTO {
    @NotNull(message = "El id del solicitante es obligatorio")
    private Long lectorId;
    @NotNull(message = "El id del libro es obligatorio")
    private Long libroId;
}