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

import itch.tsp.model.Actividad;
import itch.tsp.service.IActividadService;
import itch.tsp.service.IProyectoService;

@Controller
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private IActividadService actividadService;

    @Autowired
    private IProyectoService proyectoService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("actividades", actividadService.buscarTodas());
        return "actividad/datosActividad";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Actividad actividad = new Actividad();
        model.addAttribute("actividad", actividad);
        model.addAttribute("proyectos", proyectoService.buscarTodos());
        return "actividad/formActividad";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Actividad actividad, RedirectAttributes attributes) {
        actividadService.guardar(actividad);
        attributes.addFlashAttribute("msg", "Actividad guardada correctamente.");
        return "redirect:/actividad/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int idActividad, Model model) {
        model.addAttribute("actividad", actividadService.buscarPorId(idActividad));
        model.addAttribute("proyectos", proyectoService.buscarTodos());
        return "actividad/formActividad";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int idActividad, RedirectAttributes attributes) {
        try {
            actividadService.eliminar(idActividad);
            attributes.addFlashAttribute("msg", "Actividad eliminada con éxito.");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", "No se puede eliminar la actividad porque está asignada a un empleado.");
        }
        return "redirect:/actividad/listar";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}