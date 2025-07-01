package com.TP.ong.ModuloFamilia.Presentacion.Personas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonasController {

   @GetMapping("/personas")
public String mostrarPersonas(Model model) {
    model.addAttribute("prueba", "personas");
    return "familias/personas";  // ← personas.html
}
}