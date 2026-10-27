# README - Hito 1: Propuesta y primera app

**1. Datos del alumno y del proyecto**
* Nombre: Nerea García Vergara
* Grupo: PMDM 2.0 DAM
* Proyecto: Hito 1: Propuesta y primera app (TUPESA)

**2. Proyecto de partida y ayudas**
* Proyecto base: Plantilla inicial con Views (Empty Views Activity) adaptada a la temática de TUPESA eliminando el código innecesario.
* Recursos utilizados: Materiales y apuntes de la asignatura, internet y IA

**3. Herramientas y versiones**
* Entorno: Android Studio
* Lenguaje: Kotlin
* minSdk: 29

**4. Pasos para la ejecución**
1. Descomprimir el archivo ZIP en una carpeta local.
2. Abrir Android Studio y usar `File > Open` para seleccionar la carpeta raíz del proyecto descomprimido.
3. Esperar a que la sincronización de Gradle finalice por completo.
4. Seleccionar un emulador compatible en el menú superior.
5. Pulsar el botón verde "Run"  para compilar y ejecutar en el AVD.

**5. Archivos modificados y resumen de cambios**
* `res/values/strings.xml`: Personalización de los recursos de texto añadiendo "Bienvenido a TUPESA" y "Registrar ruta".
* `res/layout/activity_main.xml`: Añadido un control nuevo (`Button` con id `btnAccion`) debajo del texto principal.
* `MainActivity.kt`:
    - Localización del nuevo botón interactivo con `findViewById`.
    - Creación del evento `setOnClickListener` para modificar el texto visible a "¡RUTA REGISTRADA!" y registrar el evento de pulsación en Logcat.
    - Añadidas las trazas (`Log.d` con la etiqueta `PMDM`) para registrar el paso por los diferentes métodos del ciclo de vida del sistema.

**6. Perfiles probados y limitaciones conocidas**
* Perfiles AVD probados:
    - Perfil A (Limitado): Google Pixel 6.
    - Perfil B (Potente): Google Pixel 10 (API 37.2).
* Limitaciones: Es un prototipo técnico inicial ejecutado en local; no utiliza red, no conecta con GPS en tiempo real ni usa bases de datos reales. Al girar la pantalla, la Activity se recrea desde cero y el texto dinámico se pierde, volviendo a mostrar el mensaje de bienvenida inicial.