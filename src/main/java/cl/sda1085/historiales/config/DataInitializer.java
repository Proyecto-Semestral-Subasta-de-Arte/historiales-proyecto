package cl.sda1085.historiales.config;

import cl.sda1085.historiales.model.Historial;
import cl.sda1085.historiales.repository.HistorialRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    //Conexión con 'repository'
    private final HistorialRepository historialRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (historialRepository.count() > 0) {
            log.info("La base de datos de historial ya contiene datos. Omitiendo inicialización.");
            return;
        }

        log.info("Iniciando la creación de registros de auditoría...");

        //La fecha se autogenera mediante @PrePersist

        //Inicio de sesión del administrador
        Historial h1 = new Historial(
                null,
                1L,
                "Inicio de sesión",
                "Usuario",
                1L,
                null);

        //Creación de un nuevo producto (Vasija)
        Historial h2 = new Historial(
                null,
                1L,
                "Creación",
                "Producto",
                101L,
                null);

        //Apertura de subasta para el producto creado
        Historial h3 = new Historial(
                null,
                1L,
                "Creación",
                "Subasta",
                1L,
                null);

        //Usuario 5 inicia sesión para ofertar
        Historial h4 = new Historial(
                null,
                5L,
                "Inicio de sesión",
                "Usuario",
                5L,
                null);

        //Oferta recibida en la subasta #1
        Historial h5 = new Historial(
                null,
                5L,
                "Oferta recibida",
                "Oferta",
                50L,
                null);

        //Usuario 6 supera la oferta anterior
        Historial h6 = new Historial(
                null,
                6L,
                "Oferta superada",
                "Oferta",
                51L,
                null);

        //Finalización automática de la subasta
        Historial h7 = new Historial(
                null,
                1L,
                "Subasta finalizada",
                "Subasta",
                1L,
                null);

        //Intento de pago fallido por el ganador
        Historial h8 = new Historial(
                null,
                6L,
                "Pago fallido",
                "Pago",
                201L,
                null);

        //Segundo intento: Pago exitoso
        Historial h9 = new Historial(
                null,
                6L,
                "Pago exitoso",
                "Pago",
                202L,
                null);

        //Actualización de perfil del usuario ganador (dirección de envío)
        Historial h10 = new Historial(
                null,
                6L,
                "Actualización",
                "Usuario",
                6L,
                null);

        //Generación de registro de envío
        Historial h11 = new Historial(
                null,
                1L,
                "Creación",
                "Envío",
                300L,
                null);

        //Eliminación de un borrador de subasta antigua (mantenimiento)
        Historial h12 = new Historial(
                null,
                1L,
                "Eliminación",
                "Subasta",
                94L,
                null);

        //Guardado masivo de los 12 registros de trazabilidad
        historialRepository.saveAll(List.of(h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12));

        log.info("Trazabilidad completa: Se han registrado 12 eventos de auditoría exitosamente.");
    }
}
