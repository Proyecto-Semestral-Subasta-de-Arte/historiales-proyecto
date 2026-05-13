package cl.sda1085.historiales.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HistorialRequestDTO {
    @NotBlank(message = "La acción es obligatoria")
    private String accion;

    @NotBlank(message = "La entidad afectada es obligatoria")
    private String entidadAfectada;

    @NotNull(message = "El ID de la entidad es obligatorio")
    private Long idEntidad;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;


}
