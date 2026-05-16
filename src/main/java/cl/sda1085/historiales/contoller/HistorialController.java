package cl.sda1085.historiales.contoller;

import cl.sda1085.historiales.dto.HistorialRequestDTO;
import cl.sda1085.historiales.dto.HistorialResponseDTO;
import cl.sda1085.historiales.service.HistorialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/historiales")
@CrossOrigin(origins = "*")  //Permite conexión con el FrontEnd
public class HistorialController {

    //Conexión con 'service'
    private final HistorialService historialService;


    //------------------------------
    //CRUD estándar
    //------------------------------

    //Obtener todos los historiales
    @GetMapping
    public ResponseEntity <List<HistorialResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(historialService.obtenerTodos());
    }

    //Obtener historial por ID
    @GetMapping ("/{id}")
    public ResponseEntity<HistorialResponseDTO> obtenerPorId(@PathVariable Long id) {

        HistorialResponseDTO historial = historialService.obtenerPorId(id);
        return ResponseEntity.ok(historial);
    }

    //Guardar (crear) nuevo historial
    @PostMapping
    public ResponseEntity<HistorialResponseDTO> guardar
            (@Valid @RequestBody HistorialRequestDTO dto) {
        return  ResponseEntity.status(201).body(historialService.guardar(dto));
    }

    //Actualizar historial existente
    @PutMapping ("/{id}")
    public ResponseEntity<HistorialResponseDTO> actualizar (
            @PathVariable Long id,
            @Valid @RequestBody HistorialRequestDTO dto) {

        HistorialResponseDTO actualizado = historialService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    //Eliminar historial
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        historialService.obtenerPorId(id);
        historialService.eliminar(id);

        return ResponseEntity.noContent().build();
    }


    //------------------------------
    //CRUD personalizado
    //------------------------------

    //Buscar todos las acciones realizadas por un usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<HistorialResponseDTO>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(historialService.obtenerPorUsuario(idUsuario));
    }

    //Buscar historial por acción específica
    @GetMapping("/accion/{accion}")
    public ResponseEntity<List<HistorialResponseDTO>> buscarPorAccion(@PathVariable String accion) {
        return ResponseEntity.ok(historialService.buscarPorAccion(accion));
    }

    //Contar cuántas veces se ha afectado una entidad específica
    @GetMapping("/conteo/{entidad}/{idEntidad}")
    public ResponseEntity<Long> contarAccionesEntidad(@PathVariable String entidad, @PathVariable Long idEntidad) {
        return ResponseEntity.ok(historialService.contarAccionesEntidad(entidad, idEntidad));
    }

    //Verificar si un usuario ha realizado alguna acción en el sistema
    @GetMapping("/usuario/{idUsuario}/existe")
    public ResponseEntity<Boolean> verificarActividadUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(historialService.verificarActividadUsuario(idUsuario));
    }

    //Obtener la última acción registrada para una entidad específica
    @GetMapping("/ultima/{entidad}/{idEntidad}")
    public ResponseEntity<HistorialResponseDTO> obtenerUltimaAccion(@PathVariable String entidad, @PathVariable Long idEntidad) {
        return historialService.obtenerUltimaAccion(entidad, idEntidad)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
