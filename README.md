# Sistema de Gestión de Citas Médicas - API Empresarial (Grupo 5)

API RESTful desarrollada con **Spring Boot** aplicando arquitectura en N capas, persistencia relacional en PostgreSQL, manejo centralizado de excepciones, interceptores HTTP, Programación Orientada a Aspectos (AOP) e Integración Continua (CI/CD).

## Integrantes y Roles
* **Javier Salazar:** Arquitectura N capas, modelo de datos JPA y configuración de PostgreSQL.
* **Kevin Shagñay:** Capa de servicios (`Service`), reglas de negocio y consultas personalizadas.
* **Francisco Guerrón:** Controladores REST (`Controller`), DTOs y manejo centralizado de excepciones (`@RestControllerAdvice`).
* **Kevin Cruz:** Middleware (`HandlerInterceptor`), Aspectos (`AOP`), Programación Asíncrona (`@Async`) y DevOps (`GitHub Actions`).

## Requisitos Previos y Base de Datos
* **Java JDK:** 17 o superior
* **Base de datos:** PostgreSQL (`localhost:5432`)
* **Nombre de BD:** `citas_medicas_db`

```sql
CREATE DATABASE citas_medicas_db;