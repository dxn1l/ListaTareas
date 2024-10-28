# Aplicación de Lista de Tareas

## Link Repositorio

[Repositorio en GitHub](https://github.com/dxn1l/ListaTareas)

## Descripción

Lista de Tareas es una aplicación simple de gestión de tareas que ayuda a los usuarios a llevar un registro de sus tareas. La aplicación soporta múltiples idiomas, incluyendo inglés y español.

## Características

- Añadir nuevas tareas
- Marcar tareas como completadas
- Eliminar tareas
- Ver tareas pendientes y completadas
- Soporte multilingüe (inglés y español)


## Uso

1. Abre la aplicación.
2. Usa el botón "Añadir Tarea" para añadir nuevas tareas.
3. Ve las tareas en las secciones "Tareas Pendientes" y "Tareas Completadas".
4. Cambia el idioma de la aplicación cambiando la configuración de idioma del dispositivo.

## Estructura del proyecto

- `MainActivity.kt`: La actividad principal que carga la pantalla de navegación
- `TaskApp.kt`: composable que maneja la navegación entre las diferentes pantallas de la app.
- `TaskListScreen.kt`: Composable que contiene la lógica y la interfaz de usuario de la lista de tareas.
- `PendingTaskScreen.kt`: Composable que contiene la lógica y la interfaz de usuario de las tareas pendientes.
- `CompletedTaskScreen.kt`: Composable que contiene la lógica y la interfaz de usuario de las tareas completadas.
- `TaskDatabaseHelper.kt`: Clase que maneja la base de datos SQLite para almacenar las tareas.