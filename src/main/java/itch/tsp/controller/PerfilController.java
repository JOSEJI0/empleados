package itch.tsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import itch.tsp.model.Perfil;
import itch.tsp.service.IPerfilService;

@Controller
@RequestMapping("/perfiles")
public class PerfilController {

    @Autowired
    private IPerfilService perfilService;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("listaPerfiles", perfilService.buscarTodos());
        return "perfiles/listPerfiles";
    }

    @GetMapping("/create")
    public String crear(Perfil perfil) {
        return "perfiles/formPerfil";
    }

    @PostMapping("/save")
    public String guardar(Perfil perfil, RedirectAttributes attributes) {
        perfilService.guardar(perfil);
        attributes.addFlashAttribute("msg", "Perfil guardado correctamente");
        return "redirect:/perfiles/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") Integer idPerfil, RedirectAttributes attributes) {
        perfilService.eliminar(idPerfil);
        attributes.addFlashAttribute("msg", "Perfil eliminado");
        return "redirect:/perfiles/index";
    }
}