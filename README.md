# CatalogoDeLibros
![image](https://github.com/user-attachments/assets/20e47077-e76e-42b4-81cb-6100d7da02c5)

Este proyecto es una aplicación de consola en Java que permite a los usuarios buscar libros a través de la API Gutendex y almacenar la información en una base de datos PostgreSQL.

Estado del proyecto

Este proyecto está finalizado pero puede mejorarse con nuevas características y optimizaciones.


Características de la Aplicación

- **Búsqueda de libros por título**: Permite buscar libros por su título y muestra los resultados.

- 
- **Listado de todos los libros**: Muestra un listado de todos los libros almacenados en la base de datos.
- **Listado de autores**: Muestra un listado de todos los autores de los libros almacenados.
- **Listado de autores vivos en un año específico**: Muestra los autores que estaban vivos en un año específico.

Requisitos

- Java 8 o superior
- PostgreSQL
- Clave de API de [Gutendex](https://gutendex.com/)

Acceso al Proyecto

1.Clona el repositorio o descarga desde el archivo zip

2.Configura las variables de entorno en un archivo .env:

DB_URL=jdbc:postgresql://localhost:5432/tu_basededatos
DB_USERNAME=tu_usuario
DB_PASSWORD=tu_contraseña

3.Instala las dependencias:

mvn clean install

4.Ejecuta la aplicación:

mvn spring-boot:run
