package cl.sda1085.historiales.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialRequestDTO {

    @NotBlank(message = "La acción es obligatoria.")
    @Size(max = 100, message = "La acción no puede superar los 100 caracteres.")
    private String accion;

    @NotBlank(message = "La entidad afectada es obligatoria.")
    @Size(max = 50, message = "La entidad afectada no puede superar los 50 caracteres")
    private String entidadAfectada;

    @NotNull(message = "El ID de la entidad es obligatorio.")
    private Long idEntidad;

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long idUsuario;
}
