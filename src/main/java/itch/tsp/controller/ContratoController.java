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

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import itch.tsp.model.Contrato;
import itch.tsp.service.implementJPA.ContratoServiceImpJPA;
import itch.tsp.service.implementJPA.EmpleadoServiceImpJPA;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contrato")
public class ContratoController {

    @Autowired
    private ContratoServiceImpJPA contratoService;

    @Autowired
    private EmpleadoServiceImpJPA empleadoService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("contratos", contratoService.buscarTodosContrato());
        return "contrato/datosContrato";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Contrato contrato = new Contrato();
        contrato.setActivo(1);
        model.addAttribute("contrato", contrato);
        model.addAttribute("empleados", empleadoService.buscarTodosEmp());
        return "contrato/formContrato";
    }

    @PostMapping("/guardar")
    public String guardar(Contrato contrato, RedirectAttributes attributes) {
        contratoService.guardarContrato(contrato);
        attributes.addFlashAttribute("msg", "Contrato guardado correctamente.");
        return "redirect:/contrato/listar";
    }

    @GetMapping("/ver/{id}")
    public String verDetalle(@PathVariable("id") int idContrato, Model model) {
        model.addAttribute("contrato", contratoService.buscarContratoPorId(idContrato));
        return "contrato/detalleContrato";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int idContrato, Model model) {
        model.addAttribute("contrato", contratoService.buscarContratoPorId(idContrato));
        model.addAttribute("empleados", empleadoService.buscarTodosEmp());
        return "contrato/formContrato";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int idContrato, RedirectAttributes attributes) {
        // 
        contratoService.eliminarContrato(idContrato);
        attributes.addFlashAttribute("msg", "El contrato ha sido desactivado.");
        return "redirect:/contrato/listar";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(value = "nombre", required = false) String nombre,
                         @RequestParam(value = "numero", required = false) String numero,
                         @RequestParam(value = "tipo", required = false) String tipo,
                         Model model) {
        // 
        List<Contrato> lista = contratoService.buscarPorFiltros(nombre, numero, tipo);
        model.addAttribute("contratos", lista);
        return "contrato/datosContrato";
    }
    
    @GetMapping("/imprimir/{id}")
    public void imprimirContrato(@PathVariable("id") int idContrato, HttpServletResponse response) throws Exception {
        Contrato contrato = contratoService.buscarContratoPorId(idContrato);
        
        if (contrato != null) {
            // [cite: 33]
            response.setContentType("application/pdf");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=contrato_" + idContrato + ".pdf";
            response.setHeader(headerKey, headerValue);

            // [cite: 34]
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(18);

            Paragraph title = new Paragraph("DETALLES DEL CONTRATO", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("========================================================="));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("No. Contrato: " + contrato.getId()));
            document.add(new Paragraph("Número de Seguro Social (NSS): " + contrato.getNumeroSeguroSocial()));
            document.add(new Paragraph("Tipo de Contrato: " + contrato.getTipoContrato()));
            document.add(new Paragraph(" "));

            if (contrato.getEmpleado() != null) {
                document.add(new Paragraph("Datos del Empleado Asignado:"));
                document.add(new Paragraph("Nombre: " + contrato.getEmpleado().getNombreE() + " " + contrato.getEmpleado().getApellido()));
            } else {
                document.add(new Paragraph("Empleado Asignado: Ninguno"));
            }

            document.close();
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}