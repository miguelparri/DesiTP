/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TP.ong.ModuloFamilia.Presentacion.Personas;

import com.TP.ong.ModuloFamilia.Entidades.Personas;
import com.TP.ong.ModuloFamilia.Servicios.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.TP.ong.ModuloFamilia.Excepciones.Excepcion;  // <-- Importa tu excepción

@Controller
public class PersonasRegistrarEditarController {

    private final PersonaService personaService;

    public PersonasRegistrarEditarController(PersonaService personaService) {
        this.personaService = personaService;
    }

    // Mostrar formulario para nueva persona — usa template registrar
    @GetMapping("/personas/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("persona", new PersonaForm());
        model.addAttribute("titulo", "Registrar Persona");
        return "familias/agregarpersonas";  
    }

    // Mostrar formulario para editar persona — usa template editar
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
public String guardarPersona(@ModelAttribute("persona") PersonaForm form, Model model) {
    Personas persona = form.toPojo();

    if (persona.getNumeroFamilia() == null) {
        persona.setNumeroFamilia(personaService.generarNumeroFamilia());
    }

    try {
        personaService.save(persona);
    } catch (Excepcion e) {
        model.addAttribute("error", "Error al guardar la persona: " + e.getMessage());
        model.addAttribute("persona", form);
        model.addAttribute("titulo", form.getId() == null ? "Registrar Persona" : "Editar Persona");
        return "familias/editarpersonas";
    }
    return "redirect:/personas/listado";
}

}

