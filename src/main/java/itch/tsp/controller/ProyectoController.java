package itch.tsp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private ProyectoRepository proyectoRepository;
	
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("proyectos", proyectoService.buscarTodosActivos());
        return "proyecto/datosProyecto";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        if (!model.containsAttribute("proyecto")) {
            Proyecto proyecto = new Proyecto();
            proyecto.setActivo(1);
            model.addAttribute("proyecto", proyecto);
        }
        return "proyecto/formProyecto";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proyecto proyecto, RedirectAttributes attributes) {
        try {
            if (proyecto.getActivo() == null) proyecto.setActivo(1);
            proyectoService.guardar(proyecto);
            attributes.addFlashAttribute("msg", "Proyecto guardado correctamente.");
            return "redirect:/proyecto/listar";
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("error", e.getMessage());
            attributes.addFlashAttribute("proyecto", proyecto);
            return "redirect:/proyecto/nuevo";
        }
    }

    @GetMapping("/ver/{id}")
    public String verDetalle(@PathVariable("id") int id, Model model) {
        Proyecto proyecto = proyectoService.buscarPorId(id);
        model.addAttribute("proyecto", proyecto);
        if (proyecto != null) {
            model.addAttribute("actividades", proyecto.getActividades());
        }
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
        attributes.addFlashAttribute("msg", "El proyecto ha sido desactivado con éxito.");
        return "redirect:/proyecto/listar";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}