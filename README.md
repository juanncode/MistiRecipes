# Aplicación de Recetas

## Puesta en marcha
1. Crearse un archivo keys.properties en la raiz del proyecto
2. Copiar estos valores
- BASE_URL="https://demo2199037.mockable.io/"

# Descripción del Proyecto

Desarrollar una aplicación de Android utilizando Jetpack Compose que muestre una lista de recetas obtenidas desde una API simulada usando Mockable.io (https://www.mockable.io/). La aplicación debe permitir buscar recetas tanto por nombre como por ingredientes. La arquitectura del proyecto debe seguir los principios de Clean Architecture y el patrón MVVM. Se valorará adicionalmente la inclusión de pruebas unitarias, aunque no es obligatorio.

## Requisitos

### Funcionalidades Principales

1. **Lista de Recetas**:
    - Mostrar una lista de recetas obtenidas desde una API simulada en Mockable.io.
    - Cada receta debe tener los siguientes campos:
        - `id`: Identificador único.
        - `nombre`: Nombre de la receta.
        - `ingredientes`: Lista de ingredientes.
        - `preparacion`: Instrucciones de preparación.
        - `imagen`: URL de la imagen de la receta.
        - (Opcional) Añadir campos adicionales como `tiempo de preparación`, `dificultad`, etc.

2. **Búsqueda**:
    - Implementar una funcionalidad de búsqueda que permita filtrar recetas por nombre y por ingredientes.

## Tecnologías y Herramientas

- **Interfaz de Usuario**: Jetpack Compose.
- **Gestión de Estado**: ViewModel y LiveData y/o Flow.
- **Consumo de API**: Retrofit.
- **API Simulada**: Mockable.io.
- **Inyector de Dependencias**: Koin o Hilt.
- **Arquitectura**: Clean Architecture y MVVM.
- **Pruebas (Opcional)**: Se valorará la inclusión de pruebas unitarias.
