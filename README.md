# 🏕️ Planificador de Campamentos - Full Stack Java

[cite_start]Este proyecto es un **Trabajo Fin de Grado (TFG)** para el ciclo de 2º DAW, diseñado para digitalizar y optimizar la gestión logística de campamentos de verano[cite: 202, 206]. [cite_start]La aplicación permite centralizar la organización de niños, monitores, actividades y tareas en un entorno seguro y eficiente[cite: 209].

## 🚀 Características Principales

- [cite_start]**Gestión Multi-rol**: Diferenciación de funcionalidades para Coordinadores y Monitores mediante **Spring Security**[cite: 208, 211].
- [cite_start]**Control de Inventario y Alergias**: Sistema de filtrado avanzado para la seguridad alimentaria (alergias) y gestión de datos personales de acampados[cite: 212, 122].
- [cite_start]**Planificación Dinámica**: Asignación de tareas diarias para grupos y monitores con actualización de estados en tiempo real[cite: 150, 151].
- [cite_start]**Enciclopedia de Actividades**: Base de datos de juegos y dinámicas con filtros por tipo (exterior, interior, nocturnos, etc.)[cite: 97, 98].
- [cite_start]**Sistema de Notificaciones**: Alertas automáticas para monitores ante cambios en la planificación realizados por el coordinador[cite: 156, 159].

## 🛠️ Stack Tecnológico

- [cite_start]**Backend**: Java 17, Spring Boot, JUnit, JPA/Hibernate[cite: 135, 136, 141].
- [cite_start]**Frontend**: Thymeleaf, HTML5, CSS3 y JavaScript (ES6)[cite: 137, 138].
- [cite_start]**Base de Datos**: MySQL[cite: 139].
- [cite_start]**Seguridad**: Spring Security y autenticación mediante **JWT** (para API Rest)[cite: 73, 174].
- [cite_start]**Pruebas**: Test unitarios y de integración con **Mockito** y **Postman**[cite: 162, 163].
- [cite_start]**Despliegue**: Contenedores **Docker** y orquestación con Docker Compose[cite: 165, 166].

## 📂 Arquitectura y Diseño

El proyecto sigue una arquitectura modular y limpia, documentada mediante:
- [cite_start]**Diagramas Entidad-Relación**: Representación de las relaciones entre entidades como Kids, Monitores, Grupos y Tareas[cite: 108, 109].
- [cite_start]**Metodologías**: Planificación orientada a historias de usuario y metodologías ágiles[cite: 55, 69].

## 🐳 Despliegue con Docker

Para ejecutar el proyecto localmente de forma rápida:

1. Asegúrate de tener Docker instalado.
2. Ejecuta el empaquetado del proyecto:
   ```bash
   mvn clean package
