package itch.tsp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itch.tsp.model.Contrato;
import itch.tsp.service.implementJPA.ContratoServiceJpa;
import itch.tsp.service.implementJPA.EmpleadoServiceJpa;
import itch.tsp.utileria.ContratoPdfExporter;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contrato") 
public class ContratoController {

	@Autowired
	private ContratoServiceJpa contratoService;
	@Autowired
	private EmpleadoServiceJpa empleadoService;
	
	@GetMapping("/contratos")
	public String listarContratos(
			@RequestParam(name = "idSearch", required = false) Integer idSearch,
			@RequestParam(name = "tipoSearch", required = false) String tipoSearch,
			@RequestParam(name = "nombreSearch", required = false) String nombreSearch,
			Model model) {
		
		List<Contrato> listaContratos;
		
		if (idSearch != null || (tipoSearch != null && !tipoSearch.isEmpty()) || (nombreSearch != null && !nombreSearch.isEmpty())) {
			listaContratos = contratoService.buscarPorFiltros(idSearch, tipoSearch, nombreSearch);
		} else {
			listaContratos = contratoService.buscarTodos();
		}
		
		model.addAttribute("contratosLista", listaContratos);
		model.addAttribute("idSearch", idSearch);
		model.addAttribute("tipoSearch", tipoSearch);
		model.addAttribute("nombreSearch", nombreSearch);
		
		return "contratos/datosContrato";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		Contrato contrato = new Contrato();
		model.addAttribute("contrato", contrato);
		model.addAttribute("listaEmpleados", empleadoService.buscarTodosEmp());
		return "contratos/formContrato";
	}
	
	@PostMapping("/guardar")
	public String guardar(Contrato contrato, RedirectAttributes attributes) {
	    if (contrato.getId() != null) {
	        Contrato original = contratoService.buscarPorId(contrato.getId());
	        contrato.setActivo(original.getActivo());
	    } else {
	        contrato.setActivo(true);
	    }
	    contratoService.guardar(contrato);
	    attributes.addFlashAttribute("msg", "Contrato guardado");
	    return "redirect:/contrato/contratos";
	}
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable("id") Integer id, Model model) {
		Contrato contrato = contratoService.buscarPorId(id);
		model.addAttribute("contrato", contrato);
		return "contratos/verContrato";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
		Contrato contrato = contratoService.buscarPorId(id);
		model.addAttribute("contrato", contrato);
		model.addAttribute("listaEmpleados", empleadoService.buscarTodosEmp());
		return "contratos/formContrato";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer id, RedirectAttributes attributes) {
		contratoService.eliminar(id);
		attributes.addFlashAttribute("msg", "Contrato eliminado correctamente");
		return "redirect:/contrato/contratos";
	}
	
	
	@GetMapping("/exportarPdf/{id}")
	@ResponseBody
    public void exportarPdf(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=contrato_folio_" + id + ".pdf";
        response.setHeader(headerKey, headerValue);
        
        Contrato contrato = contratoService.buscarPorId(id);
        
        ContratoPdfExporter exporter = new ContratoPdfExporter(contrato);
        exporter.exportar(response);
    }
}