# Proyecto de aplicación Android – Gestión de turnos de manicura

## Descripción del proyecto

Este proyecto consiste en el desarrollo de una aplicación Android destinada a la gestión y reserva de turnos para servicios de manicura.

La aplicación estará dirigida tanto a las clientas como a la administradora del emprendimiento y tendrá como objetivo simplificar el proceso de reserva y organización de las citas.

## Problema identificado

Actualmente, cuando una clienta desea reservar varios turnos futuros debe realizar cada reserva de manera individual. Después de confirmar un turno, debe comenzar nuevamente el proceso y calcular manualmente cuándo debería realizarse su próxima cita.

Esto puede resultar repetitivo y poco práctico para las clientas que realizan sus servicios periódicamente.

## Solución propuesta

La aplicación incorporará una función de reserva de múltiples turnos.

Después de seleccionar la primera cita, la clienta podrá elegir si desea reservar próximos turnos y establecer una frecuencia, por ejemplo:

- Cada 15 días
- Cada 20 días
- Cada 30 días

La aplicación calculará las próximas fechas y permitirá consultar los horarios disponibles para reservar varias citas dentro del mismo proceso.

## Plataforma

La aplicación estará diseñada inicialmente para dispositivos Android.

Durante el desarrollo del proyecto se utilizarán:

- Android
- MIT App Inventor
- Android Studio
- GitHub

## Interfaz de usuario

Las clientas podrán:

- Consultar los servicios disponibles.
- Visualizar precios y duración.
- Seleccionar una profesional.
- Consultar fechas y horarios disponibles.
- Reservar un turno.
- Reservar varios turnos futuros.
- Consultar sus próximas citas.
- Cancelar turnos.

## Interfaz de administrador

La administradora podrá:

- Consultar la agenda.
- Visualizar las reservas.
- Administrar servicios y precios.
- Configurar horarios disponibles.
- Bloquear fechas u horarios.
- Gestionar los turnos de las clientas.

## Funcionalidad principal

La característica principal del proyecto será la posibilidad de reservar múltiples turnos sin repetir todo el proceso.

Por ejemplo, si una clienta reserva una cita y desea regresar cada 15 días, la aplicación podrá calcular las próximas fechas y mostrar la disponibilidad correspondiente.

## Diseño

El flujo principal de la aplicación será:

**Inicio → Servicio → Profesional → Fecha y horario → Próximos turnos → Frecuencia → Resumen → Confirmación**

Los wireframes del proyecto representan las principales pantallas de este proceso.

## Objetivo

El objetivo del proyecto es desarrollar una aplicación sencilla y organizada que mejore la experiencia de las clientas al reservar sus citas y facilite la gestión de la agenda del emprendimiento.


## Progreso del proyecto – Módulo 2

Durante el módulo 2 se incorporaron nuevos conceptos que permitirán comenzar a desarrollar y organizar la aplicación de gestión de turnos.

### Estructura de la aplicación

La aplicación estará organizada mediante diferentes pantallas y componentes de Android. Una Activity podrá utilizarse para representar una pantalla principal de la aplicación, mientras que los Fragments podrán utilizarse para organizar y reutilizar diferentes partes de la interfaz.

El recorrido principal de la aplicación continuará siendo:

Inicio → Servicio → Profesional → Fecha y horario → Próximos turnos → Frecuencia → Resumen → Confirmación.

También se podrán utilizar Intents para permitir la comunicación y navegación entre diferentes componentes de la aplicación.

### Uso de la nube

La computación en la nube podrá utilizarse para almacenar y mantener actualizada la información relacionada con los turnos. Esto permitirá que las clientas consulten la disponibilidad y realicen reservas mientras que la administradora podrá acceder a la información actualizada de la agenda.

En etapas posteriores del proyecto se evaluará la incorporación de servicios en la nube para funciones como almacenamiento de datos, autenticación de usuarios y notificaciones.

### Herramientas de desarrollo

Para continuar con el desarrollo del proyecto se utilizará Android Studio como entorno de desarrollo y el Android SDK como conjunto de herramientas para crear y probar la aplicación.

Git y GitHub se utilizarán para llevar un control de las diferentes versiones del proyecto, registrar los cambios realizados y publicar los avances del código y de la documentación.

### Próximos pasos

Los próximos pasos del proyecto serán comenzar a desarrollar la estructura básica de la aplicación en Android Studio, crear las primeras pantallas y continuar actualizando el código y la documentación en GitHub a medida que avance el proyecto.

## Progreso del proyecto – Módulo 5

Durante este módulo se continuó avanzando en la planificación de la aplicación de gestión de turnos, incorporando conceptos relacionados con el almacenamiento y la administración de datos en Android.

A partir de los wireframes desarrollados anteriormente, se analizó qué información necesitará almacenar la aplicación para que las diferentes pantallas puedan funcionar correctamente. Entre estos datos se encuentran las fechas, horarios, servicios y reservas realizadas por las clientas.

### Almacenamiento y base de datos

Para administrar la información de la aplicación se incorporaron conceptos relacionados con SQLite y las bases de datos en Android.

La base de datos permitirá almacenar información de los turnos para que pueda ser consultada, modificada o eliminada cuando sea necesario.

Por ejemplo, cuando una clienta seleccione una fecha, un horario y un servicio, la aplicación deberá procesar esa información y almacenarla para poder mostrar posteriormente las reservas realizadas.

El flujo general será:

Interfaz de la aplicación → Selección del turno → Procesamiento de los datos → Almacenamiento → Consulta de la información.

### Relación con los wireframes

Los wireframes realizados anteriormente permiten visualizar las principales pantallas y acciones de la aplicación. En este módulo se relacionaron esas pantallas con los datos necesarios para su funcionamiento.

Por ejemplo:

Pantalla de calendario → Selección de fecha y horario → Agregar turno → Almacenamiento de la reserva → Pantalla de confirmación.

De esta manera, el diseño de la interfaz y el almacenamiento de datos se integran dentro del desarrollo de la aplicación.

### Git y GitHub

Git y GitHub continuarán utilizándose para registrar y publicar los avances realizados durante el desarrollo del proyecto.

Git permite mantener un historial de los cambios realizados, mientras que GitHub permite almacenar y compartir el repositorio del proyecto. A medida que se incorporen nuevas funciones, los cambios podrán registrarse mediante commits y posteriormente enviarse al repositorio remoto mediante push.

## Changelog – Registro de cambios

### Cambios pasados

- Definición del problema y del objetivo de la aplicación.
- Diseño de la solución para permitir la reserva de múltiples turnos.
- Definición de las principales funciones para clientas y administradora.
- Organización del flujo principal de navegación.
- Desarrollo de los wireframes de las principales pantallas.
- Incorporación de conceptos de Activities, Fragments e Intents.
- Análisis del posible uso de servicios en la nube.
- Incorporación de Android Studio, Android SDK, Git y GitHub como herramientas del proyecto.

### Cambios actuales – Módulo 5

- Relación de los wireframes con los datos necesarios para la aplicación.
- Incorporación de conceptos de almacenamiento y bases de datos.
- Planificación del almacenamiento de fechas, horarios, servicios y reservas.
- Análisis del uso de SQLite para administrar información estructurada.
- Actualización de la documentación del proyecto.
- Uso de Git y GitHub para registrar y publicar los avances realizados.

### Cambios futuros

- Continuar desarrollando la estructura de la aplicación en Android Studio.
- Implementar el almacenamiento y recuperación de los turnos.
- Desarrollar la funcionalidad para reservar múltiples turnos.
- Incorporar la consulta y cancelación de reservas.
- Realizar pruebas y corregir posibles errores.
- Continuar actualizando el código y la documentación en GitHub.
- Preparar la versión final de la aplicación para el Módulo 8.
