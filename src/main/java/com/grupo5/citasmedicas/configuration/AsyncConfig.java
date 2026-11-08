package com.grupo5.citasmedicas.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Component
    public static class NotificacionCitaAsyncComponent {

        private static final Logger log = LoggerFactory.getLogger(NotificacionCitaAsyncComponent.class);

        @Async
        public CompletableFuture<Void> enviarConfirmacionCitaNoBloqueante(String paciente, String fechaCita) {
            log.info("[ASYNC - NO BLOQUEANTE] Ejecutando en hilo secundario ({}) envio de confirmacion para: {}",
                    Thread.currentThread().getName(), paciente);
            try {
                Thread.sleep(1000); // Simula latencia de servidor de correo externo (I/O)
                log.info("[ASYNC - COMPLETADO] Notificacion de cita ({}) enviada exitosamente a {}", fechaCita, paciente);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return CompletableFuture.completedFuture(null);
        }
    }
}