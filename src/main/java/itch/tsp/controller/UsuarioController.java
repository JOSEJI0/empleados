package itch.tsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;


import itch.tsp.model.Usuario;
import itch.tsp.service.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService serviceUsuarios;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("listaUsuarios", serviceUsuarios.buscarTodos());
        return "usuarios/listarUsuarios";
    }

    @GetMapping("/delete/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer idUsuario, RedirectAttributes attributes) {
        serviceUsuarios.eliminarU(idUsuario); // 
        attributes.addFlashAttribute("msg", "Usuario eliminado correctamente");
        return "redirect:/usuarios/index";
    }
    
    @GetMapping("/signup")
    public String registrarse(Usuario usuario, Model model) {
        return "usuarios/formRegistro";
    }
   
   
    @PostMapping("/save")
    public String guardar(Usuario usuario, RedirectAttributes attributes) {
        serviceUsuarios.guardarU(usuario);
        attributes.addFlashAttribute("msg", "¡Registro exitoso! Ya puedes iniciar sesión.");
        return "redirect:/login";
    }
    
    @GetMapping("/home")
    public String determinarDestino(Authentication auth) {
        String rol = auth.getAuthorities().toString();
        
        if (rol.contains("ROLE_ADMIN")) {
            return "redirect:/departamento/datosDepartamento";
        } else {
            return "redirect:/empleado/datosEmpleado";
        }
    }
}
