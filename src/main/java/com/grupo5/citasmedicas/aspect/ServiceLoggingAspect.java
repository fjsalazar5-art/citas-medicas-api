package com.grupo5.citasmedicas.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(ServiceLoggingAspect.class);

    @Around("execution(* com.grupo5.citasmedicas.service..*.*(..))")
    public Object auditarMetodosDeServicio(ProceedingJoinPoint joinPoint) throws Throwable {
        String metodo = joinPoint.getSignature().toShortString();
        Object[] argumentos = joinPoint.getArgs();
        long inicio = System.currentTimeMillis();

        log.info("[AOP - INICIO] Invocando servicio: {} | Argumentos: {}", metodo, Arrays.toString(argumentos));

        try {
            Object resultado = joinPoint.proceed();
            long duracion = System.currentTimeMillis() - inicio;
            log.info("[AOP - EXITO] Finalizo servicio: {} | Tiempo de ejecucion: {} ms", metodo, duracion);
            return resultado;
        } catch (Throwable ex) {
            long duracion = System.currentTimeMillis() - inicio;
            log.error("[AOP - EXCEPCION] Fallo en servicio: {} tras {} ms | Error: {}", metodo, duracion, ex.getMessage());
            throw ex;
        }
    }
}