package itch.tsp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import itch.tsp.model.Proyecto;
import itch.tsp.repository.ProyectoRepository;
import itch.tsp.service.IProyectoService;

@Controller
@RequestMapping("/proyecto")
public class ProyectoController {

	@Autowired
	private IProyectoService proyectoService;
	
	@Autowired
	private ProyectoRepository ProyectoRepository;
	
	@GetMapping("/")
	public String index(Model model) {
	    model.addAttribute("proyectosInicio", proyectoService.buscarParaInicio());
	    return "proyecto/indexInterfaz"; 
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("proyectos", proyectoService.buscarTodosActivos());
		return "proyecto/datosProyecto";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
	    Proyecto proyecto = new Proyecto();
	    proyecto.setActivo(1);
	    model.addAttribute("proyecto", proyecto);
	    return "proyecto/formProyecto";
	}

	@PostMapping("/guardar")
	public String guardar(Proyecto proyecto, RedirectAttributes attributes) {
	    try {
	        if (proyecto.getActivo() == null) proyecto.setActivo(1);
	        proyectoService.guardar(proyecto);
	        attributes.addFlashAttribute("msg", "Proyecto guardado correctamente.");
	        return "redirect:/proyecto/listar";
	    } catch (IllegalArgumentException e) { 
	        attributes.addFlashAttribute("error", e.getMessage());
	        return "redirect:/proyecto/nuevo";
	    } catch (Exception e) {
	        attributes.addFlashAttribute("error", "Error inesperado al guardar el proyecto.");
	        return "redirect:/proyecto/nuevo";
	    }
	}

	@GetMapping("/ver/{id}")
	public String verDetalle(@PathVariable("id") int id, Model model) {
		model.addAttribute("proyecto", proyectoService.buscarPorId(id));
		return "proyecto/detalleProyecto";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		Proyecto proyecto = proyectoService.buscarPorId(id);
		model.addAttribute("proyecto", proyecto);
		return "proyecto/formProyecto";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
		proyectoService.eliminarLogico(id);
		attributes.addFlashAttribute("msg", "El proyecto ha sido desactivado.");
		return "redirect:/proyecto/listar";
	}

	@GetMapping("/buscar")
	public String buscar(@RequestParam(value = "nombre", required = false) String nombre,
	                     @RequestParam(value = "inicio", required = false) Date inicio,
	                     @RequestParam(value = "fin", required = false) Date fin, 
	                     Model model) {
	    
	    List<Proyecto> lista;
	    
	    if (nombre != null && !nombre.isEmpty()) {
	        lista = ProyectoRepository.findByNombreContainingAndActivo(nombre, 1);
	    } 
	    else if (inicio != null && fin != null) {
	        lista = ProyectoRepository.findByFechaInicioBetweenAndActivo(inicio, fin, 1);
	    } 
	    else {
	        lista = proyectoService.buscarTodosActivos();
	    }
	    
	    model.addAttribute("proyectos", lista);
	    return "proyecto/datosProyecto";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}