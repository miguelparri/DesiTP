package com.TP.ong.ModuloFamilia.Presentacion.Personas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonasController {

    @GetMapping("/personas")  // ruta en minúsculas para evitar problemas
    public String mostrarPersonas(Model model) {
        model.addAttribute("prueba", "prueba envio de datos");
        return "personas";  // nombre del template Thymeleaf (personas.html)
    }
}