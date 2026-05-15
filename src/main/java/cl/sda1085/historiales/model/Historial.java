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

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(nullable = false, length = 100)
    private String accion;  //Creación, actualización, eliminación, oferta recibida, oferta superada, pago exitoso, pago fallido, subasta finalizada e inicio sesion

    @Column(name = "entidad_afectada", nullable = false, length = 50)
    private String entidadAfectada;  //Subasta, usuario, pago, producto u oferta

    @Column(name = "id_entidad", nullable = false)
    private Long idEntidad;  //ID de entidad afectada

    @Column(nullable = false)
    private LocalDateTime fecha;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
}
