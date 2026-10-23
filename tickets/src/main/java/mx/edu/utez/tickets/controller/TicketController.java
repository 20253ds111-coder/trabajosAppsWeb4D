package mx.edu.utez.tickets.controller;

import jakarta.validation.Valid;
import mx.edu.utez.tickets.controller.dto.CrearTicketDTO;
import mx.edu.utez.tickets.controller.dto.CrearUsuarioDTO;
import mx.edu.utez.tickets.controller.dto.ReasignarPrioridadDTO;
import mx.edu.utez.tickets.model.Ticket;
import mx.edu.utez.tickets.model.Usuario;
import mx.edu.utez.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody CrearUsuarioDTO dto) {
        return ResponseEntity.ok(ticketService.registrarUsuario(dto));
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody CrearTicketDTO dto) {
        return ResponseEntity.ok(ticketService.crearTicket(dto));
    }

    @PutMapping("/{ticketId}/estado")
    public ResponseEntity<Ticket> actualizarEstadoTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketService.actualizarEstadoTicket(ticketId));
    }

    @PutMapping("/{ticketId}/prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad(@PathVariable Long ticketId, @Valid @RequestBody ReasignarPrioridadDTO dto) {
        return ResponseEntity.ok(ticketService.reasignarPrioridad(ticketId, dto));
    }

    @PutMapping("/usuario/{usuarioId}/desactivar")
    public ResponseEntity<Usuario> desactivarUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.desactivarUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.obtenerTicketsUsuario(usuarioId));
    }
}