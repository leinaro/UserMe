# UserMe

Esta nueva aplicación consiste en un listado de usuarios y el detalle de
cada uno. 
• Para mostrar el listado de usuarios se usa la API https://randomuser.me
• Se esta usando la semilla de 9ddbbc1613d70f16 para evitar que el API devuelva usuarios duplicados.
• El diseño para dicha aplicación lo podrás encontrar en https://www.figma.com/file/ClN6Juu8J7mg7eHsK15AYR/Pruebatécnica?type=design&node-id=2-384&mode=design&t=YQ1q3bycxpNemjkC-0
• Opción de busqueda de usuarios por nombre o email para filtrarlos.
• Scroll infinito con paginación.

## APK
Puede instalar el apk que se encuentra en la carpeta apk/app-debug.apk

## Setup

- Configurar el IDE para usar la version 17 JDK
<img width="713" alt="Screenshot 2024-01-31 at 12 14 14 AM" src="https://github.com/leinaro/UserMe/assets/8811999/58690292-8621-4ce0-9075-2c7f52414296">

- Configurar el proyecto con Android Gradle Plugin versión 8.1.2 y Gradle versión  8.0
<img width="627" alt="Screenshot 2024-01-31 at 12 16 43 AM" src="https://github.com/leinaro/UserMe/assets/8811999/56324de8-fae5-4dae-afde-531480596a75">

- Se recomeinda usar un dispositivo o emulador API 34.

- Para asegurarse de ver el mapa correctamente, agregue al local.properties el siguiente un API key de google maps valido para el paquete com.leinaro.userme.

## Arquitectura y Patrones de diseño

### Clean architecture 
Se esta usando una arquitectura basada Clean Architecture con los niveles de UI, Domain y Data.

### Cliente-Servidor
En la capa de datos estamos utilizando retrofit para consumir los servicios del API.

### Proveedor-Consumidor
Se utiliza para enviar los datos desde el repositorio al viewmodel donde el repositorio es el proveedor y el viewmodel es el consumidor. utilizamos flows para emitir los datos.

### MVVVM
La UI está separado de la capa de datos a traves del viewmodel que es quien consume los datos del repositorio. La ui se actualiza cuando el estado de los datos en el viewmodel cambia.

### Repositorio
Es una fachada que encapsula logica de negocio, llamados a la API.

### Inyección de dependencias 
Se utiliza inyección de dependencias con hilt para reducir el acoplamiento de las clases y entre los diferentes niveles de la architectura facilitando las pruebas.

## Tests
Se incluyen tests unitarios en view model, use cases, repository y remote data sources.


