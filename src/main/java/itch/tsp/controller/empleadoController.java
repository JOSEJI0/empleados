package itch.tsp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import itch.tsp.model.Empleado;
import itch.tsp.model.Usuario;
import itch.tsp.service.implementJPA.DepartamentoServiceJpa;
import itch.tsp.service.implementJPA.EmpleadoServiceJpa;
import itch.tsp.service.implementJPA.UsuarioServiceJpa;
import itch.tsp.utileria.Utileria;


@Controller
public class EmpleadoController {
	
	@Autowired
	private EmpleadoServiceJpa empleadoService;
	
	@Autowired
	private DepartamentoServiceJpa departamentoService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioServiceJpa usuarioService;
	
	@RequestMapping("/empleado/empleados")
	public String listarEmpleados(Model model) {
		List<Empleado> listaEmpleados = empleadoService.buscarTodosEmp();
		model.addAttribute("empleadosLista", listaEmpleados);
		return "empleados/datosEmpleado";
	}
	
	@GetMapping("/empleado/crear") 
	public String crear(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		model.addAttribute("listaDepartamentos", departamentoService.buscarTodosDep());
		return "empleados/formEmpleado";
	}
	
	@PostMapping("/empleado/guardar")
	public String guardar(Empleado empleado, RedirectAttributes attributes, 
			@RequestParam("file") MultipartFile multiPart,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password) { 
		
		boolean esNuevo = (empleado.getId() == null);
		
		if (!esNuevo) {
			Empleado empleadoOriginal = empleadoService.buscarEmpPorId(empleado.getId());
			
			// Mantener datos originales que no están en el formulario
			empleado.setFechaIngreso(empleadoOriginal.getFechaIngreso());
			empleado.setActivo(empleadoOriginal.getActivo());
			
			// RESCATE DE RELACIONES PARA EVITAR JpaSystemException
			empleado.setActividades(empleadoOriginal.getActividades());
			empleado.setContratos(empleadoOriginal.getContratos());
			empleado.setHabilidades(empleadoOriginal.getHabilidades());
			empleado.setUsuario(empleadoOriginal.getUsuario());
			
			if (empleado.getEstado() == null) {
				empleado.setEstado(empleadoOriginal.getEstado());
			}
			
			// Lógica de Foto en Edición
			if (multiPart.isEmpty()) {
				empleado.setFoto(empleadoOriginal.getFoto());
			} else {
				String ruta = "./imagenes/empleados/";
				String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null) {
					empleado.setFoto(nombreImagen);
				}
			}
			
		} else {
			// Lógica para Nuevo Empleado
			empleado.setFechaIngreso(new Date());	
			empleado.setActivo(true);
			
			if (empleado.getEstado() == null) {
				empleado.setEstado(1);
			}
			
			if (!multiPart.isEmpty()) {
				String ruta = "./imagenes/empleados/"; 
				String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null) {
					empleado.setFoto(nombreImagen);
				}
			}
		}
		
		// 1. Guardar Empleado
		empleadoService.guardarEmp(empleado);
		
		// 2. Crear Usuario si es nuevo
		if (esNuevo && username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
			Usuario nuevoUsuario = new Usuario();
			nuevoUsuario.setUsername(username);
			nuevoUsuario.setPassword(passwordEncoder.encode(password));
			nuevoUsuario.setEmpleado(empleado);
			usuarioService.guardarU(nuevoUsuario);
		}

	    attributes.addFlashAttribute("msg", "Datos del empleado guardados correctamente");
	    return "redirect:/empleado/empleados";
	}
	
	@GetMapping("/empleado/editar/{id}")
	public String editar(@PathVariable ("id") int idEmpleado, Model model) {
		Empleado empleado = empleadoService.buscarEmpPorId(idEmpleado);
		model.addAttribute("empleado", empleado);
		model.addAttribute("listaDepartamentos", departamentoService.buscarTodosDep());
		return "empleados/formEmpleado";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDatabinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDatabinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/empleado/ver/{id}")
	public String verEmpleado (@PathVariable ("id") int idEmpleado, Model model) {
		Empleado empleado = empleadoService.buscarEmpPorId(idEmpleado);
		model.addAttribute("empleado", empleado);
		return "empleados/verEmpleado";
	}
	
	@GetMapping("/empleado/eliminar/{id}")
	public String eliminarEmpleado (@PathVariable ("id") int idEmpleado, RedirectAttributes attributes) {
		empleadoService.eliminarEmp(idEmpleado);
		attributes.addFlashAttribute("msg", "Empleado eliminado correctamente");
		return "redirect:/empleado/empleados";
	}
}