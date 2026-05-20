package itch.tsp.herramienta;
import java.io.File;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class Fotografia implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/imagenes/**")
				.addResourceLocations("file:/C:/empleados/imagenes/");
		
		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:/C:/departamentos/imagenesDep/");
	}
	
	public static String guardarFoto(MultipartFile multiPart, String ruta) {
		String nombreImagen = multiPart.getOriginalFilename();
		nombreImagen = nombreImagen.replace(" ", "-");
		
		String nombreFinal = UUID.randomUUID().toString() + "_" + nombreImagen;
		
		try {
			File imagen = new File(ruta + nombreFinal);
			if( !imagen.exists()) {
				imagen.mkdirs();
			}
			
			multiPart.transferTo(imagen);
			return nombreFinal;
		} catch (Exception e) {
			System.out.println("Error al guardar la imagen: " + e.getMessage());
			return null;
		}
	}
}
