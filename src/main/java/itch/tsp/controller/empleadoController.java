package itch.tsp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.herramienta.Fotografia;
import itch.tsp.model.Empleado;
import itch.tsp.service.implementJPA.DepartamentoServiceImpJPA;
import itch.tsp.service.implementJPA.EmpleadoServiceImpJPA;

@RequestMapping("/empleado")
@Controller
public class empleadoController {	
	
	@Autowired
	private EmpleadoServiceImpJPA empleadoService;
	
	@Autowired
	private DepartamentoServiceImpJPA departamentoService;
	
	@GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("empleados", empleadoService.buscarTodosEmp());
        return "empleado/datosEmpleado";
    }
	
//	empleado empleado = new empleado();
	
//	empleado.setId(1);
//	empleado.setNombre("Carlos");
//	empleado.setApellido("López");
//	empleado.setSalario(1520.15);
//	empleado.setFechaIngreso(new Date());
//	model.addAttribute("empleado", empleado);
		
	@GetMapping("/nuevo")
	public String crearEmp(Model model) {
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("departamentos", departamentoService.buscarTodos());
		return "empleado/formEmpleado";
	}
	
	@PostMapping("/guardar")
	public String guardarEmp(Empleado emp, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
		
		if (!multiPart.isEmpty()) {
			String ruta = "C:/empleados/imagenes/";
			String nombreImagen = Fotografia.guardarFoto(multiPart, ruta);
			if (nombreImagen != null) {
				emp.setFoto(nombreImagen);
			}
		}
		
		empleadoService.guardarEmp(emp);
		attributes.addFlashAttribute("msg", "Datos del empleado guardados correctamente");
		return "redirect:/empleado/listar";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	  webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/ver/{id}")
	public String verDetalleEmpleado(@PathVariable("id") int idEmpleado, Model model) {
		Empleado emp = empleadoService.buscarEmpPorId(idEmpleado);
		model.addAttribute("empleado", emp);
		return "empleado/detalleEmpleado";
		}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") int idEmpleado, Model model) {
	    Empleado emp = empleadoService.buscarEmpPorId(idEmpleado);	    
	    model.addAttribute("empleado", emp);
	    model.addAttribute("departamentos", departamentoService.buscarTodos());
	    return "empleado/formEmpleado";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idEmpleado, RedirectAttributes attributes) {
	    empleadoService.eliminarEmp(idEmpleado);
	    attributes.addFlashAttribute("msg", "El empleado ha sido desactivado exitosamente.");
	    return "redirect:/empleado/listar";
	}
}
