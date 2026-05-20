package itch.tsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.herramienta.Fotografia;
import itch.tsp.model.Departamento;
import itch.tsp.service.implementJPA.DepartamentoServiceImpJPA;


@Controller
@RequestMapping("/departamento") 

public class DepartamentoController {
	
	@Autowired
	private DepartamentoServiceImpJPA departamentoServiceImpJPA;
    
    @GetMapping("/listar") 
    public String listar(Model model) {
        model.addAttribute("departamentos", departamentoServiceImpJPA.buscarTodos());
        return "departamento/datosDepartamento";
    }

    @GetMapping("/nuevo")
    public String crear(Model model) {
        model.addAttribute("departamento", new Departamento());
        model.addAttribute("departamentos", departamentoServiceImpJPA.buscarTodos());
        return "departamento/formDepartamento";
    }

    @PostMapping("/guardar")
    public String guardar(Departamento depto, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
    	
    	if (!multiPart.isEmpty()) {
    		String ruta = "C:/departamentos/imagenesDep/";
    		String nombreImagen = Fotografia.guardarFoto(multiPart, ruta);
    		if (nombreImagen != null) {
				depto.setFotoUbicacion(nombreImagen);
			}
    	}
    	
        departamentoServiceImpJPA.guardar(depto);
        attributes.addFlashAttribute("msg", "Departamento guardado con éxito");
        return "redirect:/departamento/listar";
    }
    
    @GetMapping("/ver/{id}")
    public String verDetalle(@PathVariable("id") int idDepartamento, Model model) {
        Departamento departamento = departamentoServiceImpJPA.buscarDepPorId(idDepartamento);
        model.addAttribute("departamento", departamento);
        return "departamento/detalleDepartamento";
    } 
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int idDepartamento, Model model) {
        Departamento depto = departamentoServiceImpJPA.buscarDepPorId(idDepartamento);
        model.addAttribute("departamento", depto);
        return "departamento/formDepartamento"; 
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int idDepartamento, RedirectAttributes attributes) {
        departamentoServiceImpJPA.eliminarDepto(idDepartamento); 
        attributes.addFlashAttribute("msg", "El departamento ha sido desactivado exitosamente.");
        return "redirect:/departamento/listar";
    }
	
}