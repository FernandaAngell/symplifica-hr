# 👥 Symplifica HR Dashboard

## 📚 Descripción

Este repositorio contiene el desarrollo de una aplicación web para la gestión de recursos humanos, desarrollada con **Spring Boot** ☕ y **Vue 3** 💚 como parte de una prueba técnica.

La aplicación permite administrar empleados, beneficios y visualizar información relevante mediante un dashboard interactivo.

Durante el desarrollo se implementaron conceptos como:

- Desarrollo de APIs REST
- Arquitectura Cliente-Servidor
- Persistencia de datos con PostgreSQL 🗄️
- Integración con APIs externas (OpenStreetMap - Nominatim) 🌍
- Dashboard con estadísticas 📊
- Visualización geográfica de empleados 🗺️
- Despliegue en Render y Vercel 🚀

---

# 📂 Contenido

## 👤 Empleados

Administración completa de empleados.

- Crear empleados
- Editar información
- Eliminar registros
- Consultar detalle de cada empleado
- Búsqueda de empleados

---

## 🎁 Beneficios

Gestión de beneficios disponibles para los empleados.

- Crear beneficios
- Editar beneficios
- Eliminar beneficios
- Consultar listado

---

## 📊 Dashboard

Visualización de información general de la empresa mediante indicadores.

- Total de empleados
- Total de beneficios
- Distribución por ciudad
- Métricas generales

---

## 🗺️ Mapa de empleados

Visualización de la ubicación de los empleados utilizando **Leaflet** y **OpenStreetMap**.

Cada empleado se geolocaliza automáticamente a partir de la ciudad registrada.

---

## 🌐 API REST

Backend desarrollado con **Spring Boot**.

Incluye endpoints para:

- Gestión de empleados
- Gestión de beneficios
- Consulta de estadísticas
- Geolocalización mediante Nominatim

---

# 🛠️ Tecnologías

### Backend

- Java 21 ☕
- Spring Boot
- Spring Data JPA
- Maven
- PostgreSQL

### Frontend

- Vue 3
- TypeScript
- Vite
- Pinia
- Axios
- Leaflet

### Base de datos

- PostgreSQL

### Despliegue

- Render
- Vercel

---

# 🚀 Demo

### Frontend

https://symplifica-hr.vercel.app/login

# 🚀 Backend API

Example endpoints:

- https://symplifica-hr.onrender.com/api/employees
- https://symplifica-hr.onrender.com/api/benefits
  
---

## 🔐 Credenciales de acceso

Para acceder a la aplicación utiliza las siguientes credenciales:

```text
Usuario: admin
Contraseña: admin123
```

Una vez autenticado podrás acceder al dashboard y gestionar empleados y beneficios.

---


# 📈 Mejoras futuras

- 🔐 Autenticación con JWT
- 👥 Gestión de usuarios y roles
- 🔍 Filtros avanzados
- 📱 Diseño completamente responsive
- 📊 Más indicadores para el dashboard
- 🐳 Docker y Docker Compose

---

## 👩‍💻 Autor

**Maria Fernanda Tolosa Angel**

---
