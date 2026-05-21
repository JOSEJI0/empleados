package itch.tsp.utileria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.web.multipart.MultipartFile;

public class Utileria {

    public static String guardarArchivo(MultipartFile multiPart, String ruta) {
        String nombreOriginal = multiPart.getOriginalFilename();
        
        if(nombreOriginal != null) {
            nombreOriginal = nombreOriginal.replace(" ", "-");
        }
        
        try {
            // 1. Calculamos la ruta absoluta real (evade la carpeta temporal de Tomcat)
            Path directorioAbsoluto = Paths.get(ruta).toAbsolutePath();
            
            // 2. Si la carpeta no existe, la creamos a la fuerza
            if (!Files.exists(directorioAbsoluto)) {
                Files.createDirectories(directorioAbsoluto);
            }
            
            // 3. Unimos la ruta de la carpeta con el nombre de la foto
            Path rutaFinalArchivo = directorioAbsoluto.resolve(nombreOriginal);
            System.out.println("Guardando imagen en ruta absoluta: " + rutaFinalArchivo.toString());
            
            // 4. Copiamos el archivo directamente del flujo de datos, ignorando el transferTo()
            Files.copy(multiPart.getInputStream(), rutaFinalArchivo, StandardCopyOption.REPLACE_EXISTING);
            
            return nombreOriginal;
            
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
            return null;
        }
    }
}