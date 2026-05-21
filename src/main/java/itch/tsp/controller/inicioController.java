package itch.tsp.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import itch.tsp.model.Proyecto;
import itch.tsp.service.IProyectoService;

@Controller
public class inicioController {
	
    @Autowired
    private IProyectoService proyectoService;

    @GetMapping("/")
    public String mostrarIndex(@RequestParam(value = "nombre", required = false) String nombre,
                               @RequestParam(value = "inicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
                               @RequestParam(value = "fin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
                               Model model) {
        
        List<Proyecto> proyectos;
        
        // Evaluar si el usuario usó el formulario de filtros
        if ((nombre != null && !nombre.isEmpty()) || inicio != null || fin != null) {
            proyectos = proyectoService.buscarPorFiltrosInicio(nombre, inicio, fin);
        } else {
            // Por defecto muestra TODOS los proyectos activos sin fecha de finalización
            proyectos = proyectoService.buscarParaInicio();
        }
        
        model.addAttribute("proyectosInicio", proyectos);
        return "index"; 
    }
}