package mx.edu.utez.tickets.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean activo;

    public Usuario() {
    }
    public Usuario(String nombre, Boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }
}