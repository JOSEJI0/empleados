package itch.tsp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.model.Actividad;
import itch.tsp.model.ActividadEmpleado;
import itch.tsp.model.Empleado;
import itch.tsp.model.Proyecto;
import itch.tsp.repository.ActividadEmpleadoRepository;
import itch.tsp.repository.ActividadRepository;
import itch.tsp.service.implementJPA.EmpleadoServiceJpa;
import itch.tsp.service.implementJPA.ProyectoServiceJpa;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoServiceJpa proyectoService;
    
    // --- NUEVAS INYECCIONES DE DEPENDENCIAS ---
    @Autowired
    private EmpleadoServiceJpa empleadoService;
    
    @Autowired
    private ActividadRepository actividadRepo;
    
    @Autowired
    private ActividadEmpleadoRepository actividadEmpleadoRepo;
    // ------------------------------------------

    @GetMapping("/proyectos")
    public String listar(Model model) {
        List<Proyecto> listaProyectos = proyectoService.buscarTodos();
        model.addAttribute("proyectosLista", listaProyectos);
        return "proyectos/datosProyecto";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        return "proyectos/formProyecto";
    }

    @PostMapping("/guardar")
    public String guardar(Proyecto proyecto, RedirectAttributes attributes) {
        if(proyecto.getId() != null) {
            Proyecto original = proyectoService.buscarPorId(proyecto.getId());
            proyecto.setActivo(original.getActivo()); 
        } else {
            proyecto.setActivo(true);
        }
        
        proyectoService.guardar(proyecto);
        attributes.addFlashAttribute("msg", "Proyecto guardado correctamente");
        return "redirect:/proyectos/proyectos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Proyecto proyecto = proyectoService.buscarPorId(id);
        model.addAttribute("proyecto", proyecto);
        return "proyectos/formProyecto";
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable("id") Integer id, Model model) {
        Proyecto proyecto = proyectoService.buscarPorId(id);
        model.addAttribute("proyecto", proyecto);
        return "proyectos/verProyecto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        proyectoService.eliminar(id);
        attributes.addFlashAttribute("msg", "Proyecto eliminado correctamente");
        return "redirect:/proyectos/proyectos";
    }

    // ========================================================
    // NUEVAS RUTAS PARA ASIGNACIÓN DE EMPLEADOS A PROYECTOS
    // ========================================================

    @GetMapping("/asignar/{id}")
    public String mostrarFormularioAsignar(@PathVariable("id") Integer idProyecto, Model model) {
        // Pasamos el proyecto seleccionado
        model.addAttribute("proyecto", proyectoService.buscarPorId(idProyecto));
        
        // Pasamos la lista de empleados activos para el <select>
        model.addAttribute("listaEmpleados", empleadoService.buscarTodosEmp());
        
        return "proyectos/formAsignarProyecto";
    }

    @PostMapping("/guardarAsignacion")
    public String guardarAsignacion(@RequestParam("idProyecto") Integer idProyecto,
                                    @RequestParam("idEmpleado") Integer idEmpleado,
                                    @RequestParam("nombreActividad") String nombreActividad,
                                    @RequestParam("descripcionActividad") String descripcionActividad,
                                    RedirectAttributes attributes) {
        
        // 1. Buscamos el proyecto y el empleado en la BD
        Proyecto proyecto = proyectoService.buscarPorId(idProyecto);
        Empleado empleado = empleadoService.buscarEmpPorId(idEmpleado);

        // 2. Creamos la Actividad
        Actividad actividad = new Actividad();
        actividad.setProyecto(proyecto);
        actividad.setNombre(nombreActividad);
        actividad.setDescripcion(descripcionActividad);
        actividad.setFechaInicio(new Date());
        actividad.setActivo(true);
        actividad = actividadRepo.save(actividad); // Guardamos para que genere el ID

        // 3. Creamos el vínculo Actividad-Empleado
        ActividadEmpleado asignacion = new ActividadEmpleado();
        asignacion.setActividad(actividad);
        asignacion.setEmpleado(empleado);
        asignacion.setFechaAsignacion(new Date());
        asignacion.setActivo(true);
        actividadEmpleadoRepo.save(asignacion); // Guardamos el vínculo

        attributes.addFlashAttribute("msg", "Empleado asignado al proyecto exitosamente");
        return "redirect:/proyectos/proyectos";
    }

    // ========================================================

    // Binder para formatear las fechas
    @InitBinder
    public void initBinder(WebDataBinder webDatabinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDatabinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}