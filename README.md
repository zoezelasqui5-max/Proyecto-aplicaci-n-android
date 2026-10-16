# Proyecto de aplicación Android – Gestión de turnos de manicura

## Descripción del proyecto

AgendaDeTurnos es una aplicación móvil desarrollada para dispositivos Android, orientada a la gestión y reserva de turnos para servicios de manicura.

El proyecto surge de la necesidad de simplificar la organización de citas, especialmente para aquellas clientas que realizan servicios periódicos y necesitan programar varias reservas futuras.

La aplicación fue desarrollada utilizando Android Studio y el lenguaje de programación Kotlin. Permite registrar turnos individuales, programar reservas recurrentes, consultar las citas almacenadas y cancelar reservas. Además, incorpora mecanismos de validación de disponibilidad para evitar la superposición de turnos.

El desarrollo se realizó de manera progresiva durante los diferentes módulos de la asignatura, integrando conceptos de diseño de interfaces, programación de eventos, almacenamiento de información y control de versiones mediante Git y GitHub.

## Problema identificado

Actualmente, muchas plataformas de reserva de turnos permiten seleccionar una única cita por operación. Cuando una clienta necesita programar varios turnos futuros, debe repetir el procedimiento de reserva y calcular manualmente las fechas correspondientes.

Esta situación resulta poco práctica para los servicios de manicura, ya que las clientas suelen realizarse tratamientos de manera periódica.

Además, la organización manual de las reservas puede generar dificultades para controlar la disponibilidad de los horarios y evitar superposiciones.

## Solución propuesta

Para resolver esta problemática, se desarrolló AgendaDeTurnos, una aplicación Android que permite gestionar reservas individuales y recurrentes.

La aplicación incorpora las siguientes funcionalidades:

Reserva individual: permite seleccionar un servicio, una fecha y un horario para registrar una cita.
Reserva recurrente: permite programar varios turnos futuros a partir de una frecuencia determinada.
Validación de disponibilidad: comprueba que los horarios seleccionados estén disponibles antes de registrar una reserva.
Consulta de turnos: permite visualizar las citas previamente registradas.
Cancelación de reservas: permite eliminar turnos que ya no se desean mantener.
Almacenamiento persistente: conserva la información de las reservas para poder consultarla posteriormente.

De esta manera, la aplicación simplifica la organización de los turnos y reduce la necesidad de repetir manualmente el proceso de reserva.

## Plataforma

AgendaDeTurnos es una aplicación móvil desarrollada para dispositivos con sistema operativo Android.

Para su desarrollo se utilizaron las siguientes tecnologías y herramientas:

- **Android Studio:** entorno de desarrollo integrado utilizado para crear, ejecutar y probar la aplicación.
- **Kotlin:** lenguaje de programación empleado para implementar la lógica y las funcionalidades.
- **XML:** lenguaje utilizado para definir el diseño de las interfaces.
- **Android SDK:** conjunto de herramientas necesarias para desarrollar aplicaciones Android.
- **Gradle:** sistema utilizado para configurar y compilar el proyecto.
- **Git:** herramienta de control de versiones que permite registrar los cambios realizados.
- **GitHub:** plataforma utilizada para almacenar el código fuente y documentar el desarrollo del proyecto.

La aplicación fue ejecutada y probada mediante un emulador de Android Studio. También se verificó su compilación mediante Gradle, obteniendo el resultado BUILD SUCCESSFUL.

## Interfaz de usuario

## Interfaz de usuario

La interfaz de AgendaDeTurnos fue diseñada para ofrecer una experiencia sencilla, organizada e intuitiva.

La aplicación cuenta con diferentes pantallas que permiten acceder a las funcionalidades principales.

### Funcionalidades disponibles

- **Pantalla principal:** permite acceder a las opciones de gestión de turnos.
- **Agregar turno:** permite registrar una nueva reserva seleccionando el servicio, la fecha y el horario.
- **Turnos recurrentes:** permite programar varias reservas futuras de manera automática.
- **Mis turnos:** permite consultar las reservas registradas.
- **Cancelar turno:** permite eliminar una reserva existente.
- **Validación de disponibilidad:** comprueba que los turnos solicitados no se superpongan con otras reservas.

### Servicios de manicura

La aplicación contempla los siguientes servicios:

- Semipermanente: 30 minutos.
- Kapping: 60 minutos.
- Esculpidas: 120 minutos.
- Retiro de esmalte: 15 minutos.

La duración de cada servicio se utiliza para comprobar la disponibilidad de los horarios y evitar superposiciones entre reservas.

### Horarios de atención

Los horarios configurados en la aplicación son:

- Lunes a viernes: de 10:00 a 16:00.
- Sábados: de 10:00 a 12:00.

Estos horarios se consideran durante el proceso de validación de las reservas.

## Interfaz de administrador

AgendaDeTurnos fue diseñada considerando las necesidades de organización de un emprendimiento de manicura.

En la versión desarrollada, la gestión de las reservas se realiza mediante las siguientes funcionalidades:

- Registro de nuevos turnos.
- Consulta de las reservas almacenadas.
- Cancelación de turnos existentes.
- Verificación de disponibilidad de fechas y horarios.
- Control de la duración de los servicios.
- Validación de los horarios de atención establecidos.

Estas funciones permiten organizar las citas y reducir los conflictos entre reservas.

### Funcionalidades previstas para futuras versiones

Como posibles mejoras del proyecto, se contempla incorporar:

- Un panel de administración independiente.
- Modificación de servicios y precios desde la aplicación.
- Configuración personalizada de horarios de atención.
- Bloqueo manual de fechas y horarios.
- Gestión de múltiples profesionales.
- Autenticación de usuarios.

Estas características forman parte de la planificación futura y no de la versión implementada.

## Funcionalidad principal

La funcionalidad principal de AgendaDeTurnos es la gestión de reservas individuales y recurrentes para servicios de manicura.

### Reserva individual

La aplicación permite registrar una cita mediante la selección de un servicio, una fecha y un horario.

Antes de guardar el turno, se verifica que la reserva se encuentre dentro de los horarios de atención y que no exista una superposición con otras citas registradas.

### Reserva recurrente

La aplicación permite programar turnos futuros a partir de una frecuencia determinada.

Esta funcionalidad está orientada a las clientas que realizan servicios periódicos y necesitan organizar sus próximas citas sin repetir manualmente todo el procedimiento.

El sistema calcula las fechas correspondientes y comprueba la disponibilidad de los horarios solicitados.

### Consulta y cancelación de turnos

Las reservas registradas pueden visualizarse desde la sección "Mis turnos".

Además, la aplicación permite cancelar citas existentes, facilitando la organización de la agenda.

### Persistencia de datos

La información de las reservas se conserva mediante un mecanismo de almacenamiento local, lo que permite recuperar los turnos registrados al volver a utilizar la aplicación.

## Diseño

El flujo principal de la aplicación será:

**Inicio → Servicio → Profesional → Fecha y horario → Próximos turnos → Frecuencia → Resumen → Confirmación**

Los wireframes del proyecto representan las principales pantallas de este proceso.

## Objetivo

El objetivo general de AgendaDeTurnos es desarrollar una aplicación Android que facilite la gestión de reservas para un emprendimiento de manicura, optimizando la organización de las citas y simplificando el proceso de selección de turnos.

### Objetivos específicos

- Desarrollar una interfaz sencilla e intuitiva.
- Implementar el registro de reservas individuales.
- Incorporar la programación de turnos recurrentes.
- Validar la disponibilidad de fechas y horarios.
- Evitar superposiciones entre reservas.
- Permitir la consulta y cancelación de turnos.
- Conservar la información de las reservas mediante almacenamiento local.
- Aplicar los conocimientos adquiridos durante la asignatura de desarrollo de aplicaciones Android.
- Documentar el proceso de desarrollo mediante Git y GitHub.

El proyecto permitió integrar conocimientos de programación, diseño de interfaces, gestión de datos y control de versiones en una aplicación funcional.

### Estructura de navegación

La aplicación se organiza mediante las siguientes pantallas:

1. **Pantalla principal:** permite acceder a las funcionalidades disponibles.
2. **Agregar turno:** permite seleccionar un servicio, una fecha y un horario para registrar una reserva.
3. **Turnos recurrentes:** permite programar varias citas futuras.
4. **Mis turnos:** permite consultar las reservas almacenadas y cancelar turnos existentes.

### Componentes de la interfaz

Durante el desarrollo se utilizaron componentes visuales de Android para permitir la interacción con la aplicación.

Entre ellos se encuentran:

- TextView: presentación de textos e información.
- Button: ejecución de acciones mediante botones.
- LinearLayout: organización de los elementos visuales.
- Componentes de selección de fechas y horarios.

La navegación entre pantallas se implementó mediante Activities e Intents.

Los wireframes originales y los avances del diseño se encuentran documentados en la Wiki del repositorio.

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

El siguiente registro documenta la evolución del proyecto AgendaDeTurnos desde su planificación inicial hasta la implementación y prueba de sus funcionalidades principales.

### Cambios pasados – Módulos 1 al 5

- Identificación del problema relacionado con la reserva individual de turnos.
- Definición de los objetivos y la solución propuesta.
- Planificación de las funcionalidades para clientas y administradora.
- Elaboración de los wireframes de las principales pantallas.
- Organización del flujo de navegación.
- Investigación sobre Activities, Fragments e Intents.
- Análisis de alternativas de almacenamiento de información.
- Incorporación de Android Studio como entorno de desarrollo.
- Creación del repositorio en GitHub.
- Publicación de la documentación inicial y los avances del proyecto.

### Cambios de desarrollo – Módulos 6 y 7

- Desarrollo de la estructura de la aplicación en Android Studio.
- Implementación de las pantallas principales mediante Kotlin y XML.
- Incorporación de la navegación entre Activities.
- Desarrollo del formulario para registrar turnos.
- Configuración de los servicios de manicura y sus respectivas duraciones.
- Implementación de la lógica de disponibilidad.
- Incorporación de la programación de reservas recurrentes.
- Implementación del almacenamiento local de los turnos.
- Desarrollo de la pantalla para consultar las reservas.
- Incorporación de la funcionalidad para cancelar turnos.

### Cambios actuales – Versión final

- Integración de las funcionalidades principales de AgendaDeTurnos.
- Verificación de los horarios de atención.
- Validación de la disponibilidad para evitar superposiciones.
- Pruebas de registro, consulta y cancelación de reservas.
- Pruebas de funcionamiento mediante el emulador de Android Studio.
- Compilación satisfactoria del proyecto mediante Gradle (BUILD SUCCESSFUL).
- Actualización del código fuente en GitHub.
- Integración de los cambios en la rama principal main.
- Actualización del README con la descripción de las funcionalidades implementadas.

### Cambios futuros – Posibles mejoras

- Incorporar un sistema de autenticación de usuarios.
- Desarrollar un panel de administración independiente.
- Permitir la modificación de servicios y precios desde la aplicación.
- Incorporar la configuración personalizada de horarios.
- Agregar notificaciones y recordatorios automáticos.
- Evaluar la sincronización de reservas mediante servicios en la nube.
- Incorporar la gestión de múltiples profesionales.
- Mejorar la interfaz visual y la experiencia de usuario.
