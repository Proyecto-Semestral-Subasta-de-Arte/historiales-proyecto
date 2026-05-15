package cl.sda1085.historiales.service;

import cl.sda1085.historiales.dto.HistorialRequestDTO;
import cl.sda1085.historiales.dto.HistorialResponseDTO;
import cl.sda1085.historiales.exception.ResourceNotFoundException;
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

    //Conexión con 'repository'
    private final HistorialRepository historialRepository;

    //Método de apoyo para encapsulamiento de datos
    private HistorialResponseDTO mapToDTO(Historial historial) {
        return HistorialResponseDTO.builder()
                .id(historial.getId())
                .accion(historial.getAccion())
                .entidadAfectada(historial.getEntidadAfectada())
                .idEntidad(historial.getIdEntidad())
                .idUsuario(historial.getIdUsuario())
                .fecha(historial.getFecha())
                .build();
    }

    //CRUD estándar

    //Obtener todos los historiales
    public List<HistorialResponseDTO> obtenerTodos() {
        log.info("Consultando historial completo de auditoría");
        return historialRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //Obtener historial por ID
    public HistorialResponseDTO obtenerPorId(Long id) {
        log.info("Buscando registro de historial ID: {}", id);
        return historialRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //Guardar (crear) nuevo historial
    @Transactional
    public HistorialResponseDTO guardar(HistorialRequestDTO dto) {
        log.info("Registrando trazabilidad: Acción [{}] sobre [{}] ID: {} por Usuario: {}",
                dto.getAccion(), dto.getEntidadAfectada(), dto.getIdEntidad(), dto.getIdUsuario());

        Historial historial = new Historial();
        historial.setAccion(dto.getAccion());
        historial.setEntidadAfectada(dto.getEntidadAfectada());
        historial.setIdEntidad(dto.getIdEntidad());
        historial.setIdUsuario(dto.getIdUsuario());

        return mapToDTO(historialRepository.save(historial));
    }

    //Actualizar historial existente
    @Transactional
    public HistorialResponseDTO actualizar(Long id, HistorialRequestDTO dto) {
        log.info("Actualizando registro de historial ID: {}", id);
        return historialRepository.findById(id).map(existente -> {
            existente.setAccion(dto.getAccion());
            existente.setEntidadAfectada(dto.getEntidadAfectada());
            existente.setIdEntidad(dto.getIdEntidad());
            existente.setIdUsuario(dto.getIdUsuario());
            return mapToDTO(historialRepository.save(existente));
        })
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //Eliminar historial
    @Transactional
    public void eliminar(Long id) {
        log.warn("Eliminando registro de historial ID: {}", id);
        if (!historialRepository.existsById(id)) throw new ResourceNotFoundException(id);
        historialRepository.deleteById(id);
    }

    //CRUD personalizado

    //Buscar todos las acciones realizadas por un usuario
    public List<HistorialResponseDTO> obtenerPorUsuario(Long idUsuario) {
        log.info("Consultando historial de actividades del usuario ID: {}", idUsuario);
        return historialRepository.findByIdUsuario(idUsuario)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //Buscar historial por acción específica
    public List<HistorialResponseDTO> buscarPorAccion(String accion) {
        return historialRepository.findByAccion(accion).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //Contar cuántas veces se ha afectado una entidad específica
    public long contarAccionesEntidad(String entidad, Long idEntidad) {
        log.info("Contando registros para {} con ID: {}", entidad, idEntidad);
        return historialRepository.countByEntidadAfectadaAndIdEntidad(entidad, idEntidad);
    }

    //Verificar si un usuario ha realizado alguna acción en el sistema
    public boolean verificarActividadUsuario(Long idUsuario) {
        log.info("Verificando si el usuario {} tiene registros en el historial", idUsuario);
        return historialRepository.existsByIdUsuario(idUsuario);
    }

    //Obtener la última acción registrada para una entidad específica
    public Optional<HistorialResponseDTO> obtenerUltimaAccion(String entidad, Long idEntidad) {
        log.info("Buscando la última acción realizada sobre {} ID: {}", entidad, idEntidad);
        return historialRepository.findTopByEntidadAfectadaAndIdEntidadOrderByFechaDesc(entidad, idEntidad)
                .map(this::mapToDTO);
    }
}