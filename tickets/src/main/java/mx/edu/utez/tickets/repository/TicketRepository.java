package mx.edu.utez.tickets.repository;

import mx.edu.utez.tickets.model.Ticket;
import mx.edu.utez.tickets.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUsuario(Usuario usuario);
}