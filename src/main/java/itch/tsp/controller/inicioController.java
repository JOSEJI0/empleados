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
    public String index(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            Model model) {
        
        List<Proyecto> proyectos;
        
        if (nombre != null || fechaInicio != null) {
            proyectos = proyectoService.buscarPorFiltros(nombre, fechaInicio); 
        } else {
            proyectos = proyectoService.findProyectosActivosSinFechaFin();
        }
        
        model.addAttribute("proyectos", proyectos);
        return "index";
    }
}