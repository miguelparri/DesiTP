package com.TP.ong.ModuloFamilia.Presentacion.Familias;

import com.TP.ong.ModuloFamilia.Entidades.Personas;
import com.TP.ong.ModuloFamilia.Servicios.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.TP.ong.ModuloFamilia.DTO.FamiliaDTO;
import com.TP.ong.ModuloFamilia.Excepciones.Excepcion;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/familias")
public class FamiliasController {

    @Autowired
    private PersonaService personaService;
@GetMapping("/listado")
public String listarFamilias(@RequestParam(name = "numero", required = false) String numero, Model model) {
    List<FamiliaDTO> familias;

    List<Personas> personas = personaService.getAll();

    if (numero != null && !numero.isEmpty()) {
        try {
            Long numeroBuscado = Long.parseLong(numero);

            familias = personas.stream()
                .filter(p -> p.getNumeroFamilia().equals(numeroBuscado))
                .map(p -> new FamiliaDTO(p.getNumeroFamilia(), p.getNombreFamilia()))
                .distinct()
                .collect(Collectors.toList());

        } catch (NumberFormatException e) {
            familias = List.of(); 
        }
    } else {
        familias = personas.stream()
                .map(p -> new FamiliaDTO(p.getNumeroFamilia(), p.getNombreFamilia()))
                .distinct()
                .collect(Collectors.toList());
    }

    model.addAttribute("familias", familias);
    return "familias/listado";
}

    
    @GetMapping("/ver/{numeroFamilia}")
    public String verIntegrantesPorFamilia(@PathVariable("numeroFamilia") Long numeroFamilia, Model model) {
        List<Personas> integrantes = personaService.findByNumeroFamilia(numeroFamilia);
        System.out.println("Integrantes encontrados: " + integrantes.size());
        model.addAttribute("integrantes", integrantes);
        model.addAttribute("numeroFamilia", numeroFamilia);
        return "familias/integrantes";
    }

    @PostMapping("/borrar/{numeroFamilia}")
    public String borrarFamilia(@PathVariable Long numeroFamilia) {
        personaService.deleteByNumeroFamilia(numeroFamilia);
        return "redirect:/familias/listado";
    }
    @GetMapping("/editar/{numeroFamilia}")
public String mostrarFormularioEdicion(@PathVariable Long numeroFamilia, Model model) {
    List<Personas> integrantes = personaService.findByNumeroFamilia(numeroFamilia);
    if (integrantes.isEmpty()) {
        return "redirect:/familias/listado";
    }
    String nombreFamilia = integrantes.get(0).getNombreFamilia();
    FamiliaDTO familiaDTO = new FamiliaDTO(numeroFamilia, nombreFamilia);
    model.addAttribute("familia", familiaDTO);
    model.addAttribute("titulo", "Editar Familia " + numeroFamilia);
    return "familias/editar";  // nombre del html que creaste
}

@PostMapping("/editar")
public String guardarEdicion(@ModelAttribute FamiliaDTO familia, Model model) {
    List<Personas> integrantes = personaService.findByNumeroFamilia(familia.getNumeroFamilia());
    for (Personas p : integrantes) {
        p.setNombreFamilia(familia.getNombreFamilia());
        try {
            personaService.save(p);
        } catch (Excepcion e) {
            model.addAttribute("familia", familia);
            model.addAttribute("titulo", "Editar Familia " + familia.getNumeroFamilia());
            model.addAttribute("error", "No se pudo guardar: " + e.getMessage());
            return "familias/editar"; // Devuelve al formulario con mensaje de error
        }
    }
    return "redirect:/familias/listado";
}


}
