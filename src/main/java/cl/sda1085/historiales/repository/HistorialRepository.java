package cl.sda1085.historiales.repository;

import cl.sda1085.historiales.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {

    // Buscar todas las acciones realizadas por un usuario
    List<Historial> findByIdUsuario(Long idUsuario);

    // Opcional: Buscar por acción específica
    List<Historial> findByAccion(String accion);
}