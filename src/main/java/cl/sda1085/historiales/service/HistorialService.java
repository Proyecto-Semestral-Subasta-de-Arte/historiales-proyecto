package cl.sda1085.historiales.service;

import cl.sda1085.historiales.dto.HistorialRequestDTO;
import cl.sda1085.historiales.dto.HistorialResponseDTO;
import cl.sda1085.historiales.model.Historial;
import cl.sda1085.historiales.repository.HistorialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class HistorialService {

    private final HistorialRepository historialRepository;

    private HistorialResponseDTO convertirADTO(Historial historial) {
        return new HistorialResponseDTO(
                historial.getId(),
                historial.getAccion(),
                historial.getEntidadAfectada(),
                historial.getIdEntidad(),
                historial.getIdUsuario(),
                historial.getFecha()
        );
    }

    public List<HistorialResponseDTO> obtenerTodos() {
        log.info("Consultando historial completo");
        return historialRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());

    }

    public Optional<HistorialResponseDTO> obtenerPorId(Long id) {
        log.info("Buscando registro de historial ID: {}", id);
        return historialRepository.findById(id).map(this::convertirADTO);
    }

    @Transactional
    public HistorialResponseDTO guardar(HistorialRequestDTO dto) {
        log.info("Registrando trazabilidad: Acción [{}] sobre [{}] ID: {} por Usuario: {}",
                dto.getAccion(), dto.getEntidadAfectada(), dto.getIdEntidad(), dto.getIdUsuario());

        Historial historial = new Historial();
        historial.setAccion(dto.getAccion());
        historial.setEntidadAfectada(dto.getEntidadAfectada());
        historial.setIdEntidad(dto.getIdEntidad());
        historial.setIdUsuario(dto.getIdUsuario());

        Historial guardado = historialRepository.save(historial);
        return convertirADTO(guardado);
    }

    @Transactional
    public Optional<HistorialResponseDTO> actualizar(Long id, HistorialRequestDTO dto) {
        log.info("Actualizando registro de historial ID: {}", id);
        return historialRepository.findById(id).map(existente -> {
            existente.setAccion(dto.getAccion());
            existente.setEntidadAfectada(dto.getEntidadAfectada());
            existente.setIdEntidad(dto.getIdEntidad());
            existente.setIdUsuario(dto.getIdUsuario());
            return convertirADTO(historialRepository.save(existente));
        });
    }

    @Transactional
    public void eliminar(Long id) {
        log.warn("Eliminando registro de historial ID: {}", id);
        historialRepository.deleteById(id);
    }

    //Métodos personalizados

    public List<HistorialResponseDTO> obtenerPorUsuario(Long idUsuario) {
        log.info("Consultando historial de actividades del usuario ID: {}", idUsuario);
        return historialRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<HistorialResponseDTO> buscarPorAccion(String accion) {
        log.info("Filtrando historial por acción: {}", accion);
        return historialRepository.findByAccion(accion).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
}