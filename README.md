# TecnicoQCC — Sistema de Levantamiento e Inspección de Racks

Aplicación móvil nativa para Android desarrollada en **Kotlin** y **Jetpack Compose**, diseñada para optimizar el levantamiento, control de calidad e inspección técnica de racks industriales en terreno, con persistencia local y sincronización en la nube mediante **Xano REST API**.

## Descripción General

**TecnicoQCC** permite a los técnicos en terreno autenticar su identidad, consultar racks asignados, registrar nuevos levantamientos de inspección (mediante formulario manual o escaneo QR) y administrar su perfil profesional sincronizado en tiempo real con la nube.

### Funcionalidades Principales
* **Autenticación Multi-Perfil:** Validación de credenciales (`usuario/correo` y `contraseña`) contra la base de datos remota en Xano, cargando dinámicamente los datos y estadísticas individuales de cada técnico.
* **Panel de Control (Dashboard):** Visualización de métricas en tiempo real (racks totales e inspecciones completadas), buscador y acceso rápido a levantamientos y escáner QR.
* **Gestión de Levantamientos (Racks):** Consulta, creación y seguimiento de estados de inspección de racks (`Pendiente`, `Completada`).
* **Gestión de Perfil Sincronizado (CRUD):** Edición de información personal (`PATCH`/`POST`), eliminación de cuenta en la nube (`DELETE`) y cierre de sesión con limpieza de caché local.

---

## 🛠 Stack Tecnológico

* **Lenguaje:** Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **Arquitectura:** MVVM (Model - View - ViewModel) + Repository Pattern
* **Asincronía:** Kotlin Coroutines & StateFlow / Flow
* **Consumo de API REST:** Retrofit 2 + Gson Converter
* **Base de Datos Local:** Room Persistence Library (compilado con KSP)
* **SDK Objetivo:** Android SDK 37
* **Análisis Estático de Código:** SonarCloud

---

## Arquitectura del Proyecto (MVVM)

El proyecto implementa una separación estricta de responsabilidades bajo el patrón **MVVM**:

```text
[ UI (Jetpack Compose) ] ──▶ Observa StateFlow ──▶ [ ViewModel ]
                                                        │
                                                        ▼
                                                 [ Repository ]
                                                  /          \
                                                 ▼            ▼
                                       [ Retrofit (Xano) ]  [ Room DAO ]
