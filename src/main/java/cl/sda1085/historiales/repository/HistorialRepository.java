package cl.sda1085.historiales.repository;

import cl.sda1085.historiales.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {

    //Buscar todas las acciones realizadas por un usuario
    List<Historial> findByIdUsuario(Long idUsuario);

    //Buscar historial por acción específica
    List<Historial> findByAccion(String accion);

    //Contar cuántas veces se ha afectado una entidad específica
    long countByEntidadAfectadaAndIdEntidad(String entidadAfectada, Long idEntidad);

    //Verificar si un usuario ha realizado alguna acción en el sistema
    boolean existsByIdUsuario(Long idUsuario);

    //Obtener la última acción registrada para una entidad específica
    Optional<Historial> findTopByEntidadAfectadaAndIdEntidadOrderByFechaDesc(String entidadAfectada, Long idEntidad);
}
