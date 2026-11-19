Proyecto "Recetario" AD05 Tarea Evaluativa 1

Este proyecto es una aplicación web desarrollada para la tarea de evaluación de la unidad AD05 

Versiones:
 - Java 21
 - Spring Boot 4.0.8
 - Maven 3.x (gestionado por el IDE)
 - Base de Datos Oracle XE 21c (Servicio xepdb1)
 - Servidor Web Tomcat embebido (puerto 8083)

!. Configuración de la Base de Datos (Oracle):

Para que la aplicación funcione, la base de datos debe estar encendida y con el usuario preparado.

1. Conéctate a Oracle como administrador (ej. "SYSTEM") al servicio "xepdb1".
2. Ejecuta el siguiente script para crear el usuario de prácticas con la contraseña indicada, de lo contrario no funcionara:

   CREATE USER RECETARIO IDENTIFIED BY "recetario";
   GRANT CREATE SESSION, CREATE TABLE, CREATE SEQUENCE TO RECETARIO;
   ALTER USER RECETARIO QUOTA 20M ON USERS;
   
2. Crea la conexión en Oracle SQL Developer.

3. Ejecuta la clase principal RecetarioApplication.java (Clic derecho → Run As → Java Application).

NOTA: Tengo que repasar la unidad anterior en busca de como crear correctamente el Readme, supongo que se explica en algun punto.