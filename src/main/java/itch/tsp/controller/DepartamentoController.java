package itch.tsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.model.Departamento;
import itch.tsp.service.implementJPA.DepartamentoServiceJpa;
import itch.tsp.utileria.Utileria;


@Controller
@RequestMapping("/departamento")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoServiceJpa departamentoService;
	
	@GetMapping("/departamentos") 
	public String listarDepartamentos(Model model) {
		List<Departamento> listaDepartamentos = departamentoService.buscarTodosDep();
		model.addAttribute("departamentosLista", listaDepartamentos);
		return "departamento/datosDepartamento";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		Departamento departamento = new Departamento();
		model.addAttribute("departamento", departamento);
		return "departamento/formDepartamentos";
	}	
	
	@PostMapping("/guardar")
	public String guardar(Departamento departamento, RedirectAttributes attributes, @RequestParam("file") MultipartFile multipart) {
		//Guardar imagen en la carpeta D:/imagenes/ y establecer la ruta en el objeto departamento
		//Recuperar ID
		if(departamento.getId() != null) {
			//Departamento Existente
			Departamento departamentoOriginal = departamentoService.buscarDepPorId(departamento.getId());
			departamento.setActivo(departamentoOriginal.getActivo());
			if(multipart.isEmpty()) {
				departamento.setFotoDep(departamentoOriginal.getFotoDep());
			}
			
		}else {
			//Nuevo Departamento
			departamento.setActivo(true);
		}
		//Imagen nueva
			if(!multipart.isEmpty()) {
				String ruta = "D:/imagenes/departamentos/";
				String nombreImagen = Utileria.guardarArchivo(multipart, ruta);
				if(nombreImagen != null) {
					departamento.setFotoDep(nombreImagen);
				}
			}
		
		
		System.out.println("Departamento Guardado: " + departamento.getNombre());
	    attributes.addFlashAttribute("msg", "Datos del departamento guardados correctamente");
	    departamentoService.guardarDep(departamento);
	    return "redirect:/departamento/departamentos"; 
	}

	@GetMapping("/ver/{id}")
	public String verDepartamento(@PathVariable("id") int idDepartamento, Model model) {
		Departamento departamento = departamentoService.buscarDepPorId(idDepartamento);
		model.addAttribute("departamento", departamento);
		return "departamento/verDepartamento";
	}

	@GetMapping("/editar/{id}")
	public String editarDepartamento(@PathVariable("id") int idDepartamento, Model model) {
		Departamento departamento = departamentoService.buscarDepPorId(idDepartamento);
		model.addAttribute("departamento", departamento);
		return "departamento/formDepartamentos";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarDepartamento(@PathVariable("id") int idDepartamento, RedirectAttributes attributes) {
		departamentoService.eliminarDep(idDepartamento);
		attributes.addFlashAttribute("msg", "Departamento eliminado correctamente");
		return "redirect:/departamento/departamentos";
	}
	
	
}