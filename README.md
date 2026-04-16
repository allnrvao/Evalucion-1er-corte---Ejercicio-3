# 🛒 App Registro de Pedidos - Pulpería "La Bendición"

Este proyecto presenta una solución móvil moderna desarrollada en **Jetpack Compose**, aplicando los lineamientos de **Material Design 3 (M3)**. La aplicación permite a los tenderos registrar pedidos de clientes de forma rápida, clara y eficiente.

---

## 🛠️ Justificación Técnica de la Solución

Para el desarrollo se han seguido los criterios de evaluación establecidos en la rúbrica, asegurando la calidad tanto en el código como en la experiencia de usuario (UX).

### 1. Implementación Funcional de la Interfaz (6 pts)
La aplicación cumple con el 100% de los requerimientos funcionales del caso:
* **Encabezado:** Título "Pedido de Pulpería" integrado en un `CenterAlignedTopAppBar`.
* **Captura de Datos:** Tres campos de texto claros para Nombre, Producto y Cantidad.
* **Acción:** Botón de registro con respuesta visual inmediata.
* **Visualización:** Uso de un `ElevatedCard` dinámico que muestra el resumen del pedido una vez procesado.

### 2. Aplicación de Material Design 3 (6 pts)
Se implementaron los principios fundamentales de M3 para una estética moderna y funcional:
* **MaterialTheme:** Uso de un `ColorScheme` tonal que garantiza contraste y accesibilidad.
* **Componentes M3:** Implementación de `Scaffold`, `OutlinedTextField`, `Button`, `ElevatedCard` y `Surface`.
* **Jerarquía Visual:** Uso de diferentes pesos tipográficos (`Black`, `Bold`, `Medium`) y elevación para separar el formulario del área de resultados.

### 3. Manejo de Interacción y Estado (8 pts)
La reactividad de la app se basa en las mejores prácticas de Compose:
* **State Hoisting:** El estado de los campos de texto se maneja mediante `remember { mutableStateOf("") }`.
* **Interfaz Dinámica:** La tarjeta de confirmación solo aparece cuando el estado `resumenPedido` deja de ser nulo, aplicando una lógica declarativa pura.
* **Validación Simple:** El botón incluye una validación básica para asegurar que los campos no estén vacíos antes de generar el registro.

### 4. Trabajo Colaborativo y Control de Versiones (5 pts)
El código fue estructurado en **4 partes lógicas** para permitir que el equipo trabajara simultáneamente sin conflictos de archivos:
1.  **Parte 1:** Definición de Temas y Estilos (Brand Identity).
2.  **Parte 2:** Lógica del Formulario y Captura (Input logic).
3.  **Parte 4:** Componentes de Visualización (Output logic).
4.  **Parte 4:** Orquestación Final y Scaffold (Main integration).

### 5. Organización Final del Proyecto (5 pts)
El proyecto se entrega en un solo archivo consolidado, pero estrictamente modularizado mediante funciones `@Composable` independientes, lo que facilita el mantenimiento y la escalabilidad futura.

---

## 📐 Arquitectura de la Interfaz

| Componente | Uso en la Aplicación |
| :--- | :--- |
| **Scaffold** | Estructura base que gestiona la barra superior y el padding de la pantalla. |
| **OutlinedTextField** | Campos de entrada con iconos internos para mejorar la asequibilidad (affordance). |
| **ElevatedCard** | Contenedor para el resumen del pedido que resalta sobre el fondo mediante sombras dinámicas. |
| **Surface** | Utilizado para definir capas de color de fondo que cumplen con los estándares de contraste. |

---

## 🚀 Cómo ejecutar el proyecto
1. Copie el código fuente proporcionado en su archivo `MainActivity.kt`.
2. Asegúrese de tener las dependencias de **Material3** actualizadas en su archivo `build.gradle`.
3. Ejecute la aplicación en un dispositivo con API 24 o superior.

---
*Este proyecto fue desarrollado bajo los estándares de programación declarativa y diseño centrado en el usuario.*
