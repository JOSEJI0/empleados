@echo off
echo === Deteniendo contenedor viejo ===
docker stop app-empleados
docker rm app-empleados

echo === Compilando nuevo JAR (Generando archivo...) ===
:: Usamos CALL para que el script espere a Maven
call mvnw clean package -DskipTests

echo === Construyendo nueva imagen ===
:: Solo si el JAR se creo con éxito, construimos
if exist target\empleados-0.0.1-SNAPSHOT.jar (
    docker build -t proyecto-empleados:v1 .
) else (
    echo ERROR: El archivo JAR no se encontro. Revisa los errores de compilacion arriba.
    pause
    exit
)

echo === Lanzando contenedor actualizado ===
docker run -d -p 8080:8080 --name app-empleados -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/empleado proyecto-empleados:v1

echo === LISTO! Refresca tu navegador en localhost:8080 ===
pause