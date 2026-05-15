package cl.sda1085.historiales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialResponseDTO {

        private Long id;
        private String accion;
        private String entidadAfectada;
        private Long idEntidad;
        private Long idUsuario;
        private LocalDateTime fecha;
}
