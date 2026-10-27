# TUPESA · UT2.1 Listado, detalle y formulario

**Alumna:** Nerea García Vergara · **Grupo:** PMDM 2.º DAM · IES Polígono Sur
**Práctica:** UT2.1 · Listado, detalle y formulario (primera parte de H2)
**Repositorio:** https://github.com/lcfxr/UT2_1_PMDM


---

## 1. Temática

**TUPESA** (Transportes Unidos de los Pueblos del Entorno Sevillano) es la app que propuse en H1 para las personas que usan los **autobuses interurbanos de Sevilla**.

En esta práctica la app gestiona **líneas de autobús**: permite **consultar** las líneas, **ver el detalle** de una línea (su recorrido) y **añadir** una línea nueva desde un formulario.

La app se abre con la **pantalla de bienvenida de H1** («Bienvenido a TUPESA»), que he conservado como pantalla de inicio. Desde ella, el botón **Ver líneas** lleva al listado.

Todos los datos son **ficticios** y se guardan solo en memoria (sin base de datos, sin internet y sin permisos).

> Cambio respecto a H1: en H1 el formulario era de registro de usuario (ID, email y contraseña). En UT2.1 el formulario pasa a **crear una línea nueva**, que es el registro que se muestra en la lista.

## 2. Entidad y campos

Clase de datos `Linea` (archivo `Linea.kt`):

| Campo | Tipo | Visible | Función |
|---|---|---|---|
| `id` | `Int` | No | Identificador único de cada línea (1, 2, 3…). Las nuevas reciben el mayor id + 1 |
| `codigo` | `String` | Sí (lista y detalle) | Código de la línea, por ejemplo `M-163`. Es lo que se ve en cada fila de la lista |
| `recorrido` | `String` | Sí (detalle) | Por dónde va la línea, por ejemplo `Sevilla – Camas` |

Registros de ejemplo (ficticios):

| id | codigo | recorrido |
|---|---|---|
| 1 | M-163 | Sevilla – Hospital San Juan |
| 2 | M-160 | Sevilla – Camas |
| 3 | M-140 | Sevilla – Santiponce |

## 3. Reglas de entrada

Se aplican en `FormularioActivity` al pulsar **Guardar**, siempre **después de `trim()`**:

| Campo | Regla | Mensaje si falla |
|---|---|---|
| Código | Entre 2 y 80 caracteres | "El código debe tener al menos 2 caracteres" |
| Recorrido | Obligatorio, máximo 80 caracteres | "El recorrido es obligatorio" |

- El máximo de 80 se controla con `android:maxLength="80"` en los dos `EditText`.
- Si hay error: se muestra el mensaje en el campo (`error`), el cursor va al **primer campo incorrecto** (`requestFocus()`) y **no se borra** lo escrito.
- Si todo es correcto, el formulario devuelve los datos al listado con la **API de Activity Result** y el listado añade la línea **una sola vez** y refresca el adaptador.
- **Cancelar** o **Atrás** cierran el formulario sin añadir nada.

## 4. Pantallas y navegación

| Pantalla | Archivos | Contenido |
|---|---|---|
| Bienvenida (inicio, de H1) | `BienvenidaActivity.kt` · `activity_bienvenida.xml` | «Bienvenido a TUPESA» y botón **Ver líneas**. Es la Activity `LAUNCHER` del Manifest |
| Listado | `MainActivity.kt` · `activity_main.xml` | `ListView` con las líneas, botón **Añadir línea** y botón **Opciones** (acción **Ayuda**) |
| Detalle | `DetalleActivity.kt` · `activity_detalle.xml` | Código y recorrido de la línea tocada, botón **Volver** |
| Formulario | `FormularioActivity.kt` · `activity_formulario.xml` | 2 campos con etiqueta, **Guardar** y **Cancelar**, dentro de un `ScrollView` |

- Las tres pantallas que pide la práctica son Listado, Detalle y Formulario. La Bienvenida es una pantalla extra que conservo de H1.
- Bienvenida → Listado: `startActivity(Intent(this, MainActivity::class.java))`. Atrás desde el listado vuelve a la bienvenida.
- Listado → Detalle: `Intent` + `putExtra` (código y recorrido) → `startActivity`. El detalle los lee con `getStringExtra`. **Volver** llama a `finish()`.
- Listado → Formulario: `registerForActivityResult(StartActivityForResult())` → `launch`. El formulario devuelve `RESULT_OK` con los datos o `RESULT_CANCELED`.
- Todos los textos de la interfaz están en `res/values/strings.xml`.
- En el Manifest, `FormularioActivity` usa `android:windowSoftInputMode="adjustResize"` para que el teclado no tape **Guardar**.

## 5. Cómo ejecutar

1. Abrir Android Studio → **Open** → seleccionar la carpeta del proyecto.
2. Esperar a que termine **Gradle sync**. Si aparece el aviso *Sync Android SDKs*, aceptar (Android Studio ajusta `local.properties` con la ruta del SDK del equipo).
3. Elegir un emulador (probado en **Pixel 6** y **Pixel 10**, API 37) y pulsar **Run ▶**.
4. Uso: en la bienvenida pulsar **Ver líneas** · tocar una línea para ver su recorrido · **Añadir línea** para crear una nueva · **Opciones → Ayuda** para ver las instrucciones.

## 6. Versiones del entorno

| Elemento | Versión |
|---|---|
| Android Studio | `[PON TU VERSIÓN: Help → About]` |
| Android Gradle Plugin | 9.4.1 |
| Gradle | 9.6.0 |
| compileSdk / targetSdk | 37 |
| minSdk | 24 |
| Java (source/target) | 11 |
| Lenguaje | Kotlin + XML (vistas) |
| Librerías | AppCompat 1.6.1 · Activity KTX 1.8.0 · Material 1.10.0 · Core KTX 1.10.1 |
| Emuladores | Pixel 6 (API 37) · Pixel 10 (API 37) |

## 7. Limitaciones conocidas

- **Pérdida de estado al girar:** si se añade una línea y se gira el móvil, la línea nueva desaparece, porque la Activity se destruye y se vuelve a crear con las 3 líneas de ejemplo. Se analizará y corregirá en UT2.2 (detalle en `pruebas.pdf`).
- Los datos no se guardan al cerrar la app (no hay base de datos en esta práctica).

## 8. Recursos y ayudas utilizados

- Documentación de la asignatura: UT2.0 (ejemplo *Mi biblioteca*), *Guía Android Studio desde cero* y *Android desde cero: qué es cada cosa*.
- Mi entrega de H1 (TUPESA) como punto de partida.
- Asistente de IA (Claude) como guía paso a paso, para explicar el código y revisar errores.
- Layouts de Android: `android.R.layout.simple_list_item_1` para las filas de la lista.
