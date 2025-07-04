package com.TP.ong.ModuloFamilia.Presentacion.Personas;

import com.TP.ong.ModuloFamilia.Servicios.PersonaService;
import com.TP.ong.ModuloFamilia.Entidades.Personas;
import jakarta.validation.Valid; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/buscarpersonas")
public class PersonasBuscarController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public String mostrarFormularioBusqueda(Model model) {
        model.addAttribute("formBean", new PersonasBuscarForm());
        model.addAttribute("mensajebuscar", "Búsqueda de personas");
        return "familias/buscarpersonas";
    }

    @PostMapping
public String procesarBusqueda(
        @ModelAttribute("formBean") @Valid PersonasBuscarForm formBean,
        BindingResult result,
        Model model,
        @RequestParam String action) {

    if ("actionBuscar".equals(action)) {
        if (result.hasErrors()) {
            return "familias/buscarpersonas";
        }

        try {
            List<Personas> resultados = personaService.filter(formBean);  // <- cambio aquí
            model.addAttribute("resultados", resultados);
        } catch (Exception e) {
            result.reject("error.global", e.getMessage());
        }

        model.addAttribute("mensajebuscar", "Búsqueda de personas");
        return "familias/buscarpersonas";
    } else if ("actionCancelar".equals(action)) {
        return "redirect:/";
    } else if ("actionRegistrar".equals(action)) {
        return "redirect:/personas/editar";
    }

    return "redirect:/";
}
}