package com.TP.ong.ModuloFamilia.Presentacion.Personas;

import com.TP.ong.ModuloFamilia.Entidades.Personas;
import com.TP.ong.ModuloFamilia.Servicios.PersonaService;
import com.TP.ong.ModuloFamilia.Excepciones.Excepcion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class PersonasRegistrarEditarController {

    private final PersonaService personaService;

    public PersonasRegistrarEditarController(PersonaService personaService) {
        this.personaService = personaService;
    }

    // Mostrar formulario para nueva persona
    @GetMapping("/personas/nueva")
    public String mostrarFormularioNueva(Model model) {
        if (!model.containsAttribute("persona")) {
            model.addAttribute("persona", new PersonaForm());
        }
        model.addAttribute("titulo", "Registrar Persona");
        return "familias/agregarpersonas";  
    }

    // Mostrar formulario para editar persona 
    @GetMapping("/personas/editar/{id}")
public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
    Personas persona = personaService.getById(id);
    if (persona == null) {
        return "redirect:/error";
    }
    model.addAttribute("persona", new PersonaForm(persona));
    model.addAttribute("titulo", "Editar Persona");
    return "familias/editarpersonas";
}
    // Guardar persona (nuevo o edición)
   @PostMapping("/personas/guardar")
public String guardarPersona(@Valid @ModelAttribute("persona") PersonaForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    
    if (bindingResult.hasErrors()) {
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.persona", bindingResult);
        redirectAttributes.addFlashAttribute("persona", form);
        redirectAttributes.addFlashAttribute("titulo", form.getId() == null ? "Registrar Persona" : "Editar Persona");
        
        if (form.getId() == null) {
            return "redirect:/personas/nueva";
        } else {
            return "redirect:/personas/editar/" + form.getId();
        }
    }

    Personas persona = form.toPojo();

    if (persona.getNumeroFamilia() == null) {
        persona.setNumeroFamilia(personaService.generarNumeroFamilia());
    }

    try {
        personaService.save(persona);
    } catch (Excepcion e) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        redirectAttributes.addFlashAttribute("persona", form);
        redirectAttributes.addFlashAttribute("titulo", form.getId() == null ? "Registrar Persona" : "Editar Persona");
        
        if (form.getId() == null) {
            return "redirect:/personas/nueva";
        } else {
            return "redirect:/personas/editar/" + form.getId();
        }
    }

    redirectAttributes.addFlashAttribute("mensaje", "¡Persona guardada correctamente!");
    return "redirect:/personas/nueva";
}

@PostMapping("/personas/eliminar/{id}")
public String eliminarPersona(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
        personaService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Persona eliminada correctamente.");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error al eliminar la persona: " + e.getMessage());
    }
    return "redirect:/buscarpersonas";  
}

}
