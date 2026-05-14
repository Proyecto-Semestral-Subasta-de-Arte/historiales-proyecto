package cl.sda1085.historiales.config;

import cl.sda1085.historiales.model.Historial;
import cl.sda1085.historiales.repository.HistorialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor

public class DataInitializer implements CommandLineRunner {

    private final HistorialRepository historialRepository;

    private Historial

}
