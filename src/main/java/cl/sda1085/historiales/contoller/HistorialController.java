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
@RequestMapping ("/api/usuarios")


public class HistorialController {

    //Conexión con 'service'
    private final HistorialService historialService;

    @GetMapping
    public ResponseEntity <List<HistorialResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(historialService.obtenerTodos());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<HistorialResponseDTO> obtenerPorId(@PathVariable Long id) {

        HistorialResponseDTO historial = historialService.obtenerPorId(id);
        return ResponseEntity.ok(historial);
    }

    @PostMapping
    public ResponseEntity<HistorialResponseDTO> guardar
            (@Valid
             @RequestBody HistorialRequestDTO dto) {
        return  ResponseEntity.status(201).body(historialService.guardar(dto));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<HistorialResponseDTO> actualizar (
            @PathVariable Long id,
            @Valid @RequestBody HistorialRequestDTO dto) {

        HistorialResponseDTO actualizado = historialService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        historialService.obtenerPorId(id);
        historialService.eliminar(id);

        return ResponseEntity.noContent().build();
    }



}
