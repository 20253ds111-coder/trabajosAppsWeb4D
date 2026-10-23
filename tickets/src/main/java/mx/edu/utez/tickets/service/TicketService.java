package mx.edu.utez.tickets.service;

import mx.edu.utez.tickets.controller.dto.CrearTicketDTO;
import mx.edu.utez.tickets.controller.dto.CrearUsuarioDTO;
import mx.edu.utez.tickets.controller.dto.ReasignarPrioridadDTO;
import mx.edu.utez.tickets.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.tickets.model.EstadoTicket;
import mx.edu.utez.tickets.model.Ticket;
import mx.edu.utez.tickets.model.Usuario;
import mx.edu.utez.tickets.repository.TicketRepository;
import mx.edu.utez.tickets.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Usuario registrarUsuario(CrearUsuarioDTO dto) {
        Usuario usuario = new Usuario(dto.getNombre(), dto.getActivo());
        return usuarioRepository.save(usuario);
    }

    public Ticket crearTicket(CrearTicketDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new CustomBadRequestException("El usuario no existe"));

        if (!usuario.getActivo()) {
            throw new CustomBadRequestException("El usuario no esta activo");
        }

        Ticket ticket = new Ticket(dto.getDescripcion(), EstadoTicket.ABIERTO, dto.getPrioridad(), usuario);
        return ticketRepository.save(ticket);
    }

    public Ticket actualizarEstadoTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new CustomBadRequestException("El ticket no existe"));

        if (ticket.getEstado().equals(EstadoTicket.ABIERTO)) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else if (ticket.getEstado().equals(EstadoTicket.EN_PROCESO)) {
            ticket.setEstado(EstadoTicket.CERRADO);
        } else if (ticket.getEstado().equals(EstadoTicket.CERRADO)) {
            throw new CustomBadRequestException("El ticket ya esta CERRADO y no se puede modificar");
        }

        return ticketRepository.save(ticket);
    }

    public Ticket reasignarPrioridad(Long ticketId, ReasignarPrioridadDTO dto) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new CustomBadRequestException("El ticket no existe"));

        if (ticket.getEstado().equals(EstadoTicket.CERRADO)) {
            throw new CustomBadRequestException("No se puede cambiar la prioridad de un ticket cerrado");
        }

        ticket.setPrioridad(dto.getPrioridad());
        return ticketRepository.save(ticket);
    }

    public Usuario desactivarUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new CustomBadRequestException("El usuario no existe"));

        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }

    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new CustomBadRequestException("El usuario no existe"));

        return ticketRepository.findByUsuario(usuario);
    }
}