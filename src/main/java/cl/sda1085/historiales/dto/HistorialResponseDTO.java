package cl.sda1085.historiales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class HistorialResponseDTO {

        private Long id;
        private String accion;
        private String entidadAfectada;
        private Long idEntidad;
        private Long idUsuario;
        private LocalDateTime fecha;
}

