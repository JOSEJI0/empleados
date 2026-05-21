package itch.tsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.model.Actividad;
import itch.tsp.service.IActividadService;
import itch.tsp.service.IProyectoService;

@Controller
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private IActividadService deptoService; // Tu servicio de actividades

    @Autowired
    private IProyectoService proyectoService; // Para poder asignar un proyecto a la actividad

    @GetMapping("/listar")
    public String listarActividades(Model model) {
        // Vinculamos la lista bajo la clave que tus HTMLs esperan leer
        model.addAttribute("actividades", deptoService.buscarTodas());
        return "actividad/datosActividad"; // Carpeta actividad, archivo datosActividad.html
    }

    @GetMapping("/nuevo")
    public String crear(Model model) {
        model.addAttribute("actividad", new Actividad());
        model.addAttribute("proyectos", proyectoService.buscarTodos()); // Lista desplegable de proyectos
        return "actividad/formActividad";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Actividad actividad, RedirectAttributes attributes) {
        deptoService.guardar(actividad);
        attributes.addFlashAttribute("msg", "Actividad procesada exitosamente.");
        return "redirect:/actividad/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer idActividad, Model model) {
        model.addAttribute("actividad", deptoService.buscarPorId(idActividad));
        model.addAttribute("proyectos", proyectoService.buscarTodos());
        return "actividad/formActividad";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer idActividad, RedirectAttributes attributes) {
        deptoService.eliminar(idActividad);
        attributes.addFlashAttribute("msg", "Actividad eliminada del sistema.");
        return "redirect:/actividad/listar";
    }
}