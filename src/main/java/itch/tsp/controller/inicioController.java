package itch.tsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class inicioController {
	
	@GetMapping("/")
    public String mostrarIndex() {
        return "index"; // Esto busca templates/index.html
    }
}