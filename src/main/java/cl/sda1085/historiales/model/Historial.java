package cl.sda1085.historiales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "historiales")

public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accion; // "creación", "actualización", "eliminación", "oferta_recibida", "oferta_superada", "pago_exitoso", "pago_fallido", "subasta_finalizada","inicio_sesion"

    @Column(nullable = false)
    private String entidadAfectada; // "subasta", "usuario", "pago", "producto", "oferta"

    @Column(nullable = false)
    private Long idEntidad; // ID de entidad afectada

    @Column(nullable = false)
    private Long idUsuario; // Quién generó la acción

    @Column(nullable = false)
    private LocalDateTime fecha;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
}