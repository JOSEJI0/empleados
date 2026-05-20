# Usamos una imagen ligera de Java 21 (basada en Alpine para que pese menos)
FROM eclipse-temurin:21-jdk-alpine

# Directorio donde vivirá la app dentro del contenedor
WORKDIR /app

# Copiamos el JAR generado desde tu carpeta target local al contenedor
COPY target/empleados-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto 8080 (el default de Spring Boot)
EXPOSE 8080

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]