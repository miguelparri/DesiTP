package com.TP.ong.ModuloFamilia.Presentacion.Familias;

import com.TP.ong.ModuloFamilia.Entidades.Personas;
import com.TP.ong.ModuloFamilia.Servicios.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/familias")
public class FamiliasController {

    @Autowired
    private PersonaService personaService;

        @GetMapping("/listado")
    public String listarFamilias(@RequestParam(name = "numero", required = false) String numero, Model model) {
        List<Long> numerosDeFamilia;

        if (numero != null && !numero.isEmpty()) {
            try {
                Long numeroBuscado = Long.parseLong(numero);
                numerosDeFamilia = personaService.getAll()
                        .stream()
                        .map(Personas::getNumeroFamilia)
                        .distinct()
                        .filter(num -> num.equals(numeroBuscado))
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                numerosDeFamilia = List.of(); // número inválido → lista vacía
            }
        } else {
            numerosDeFamilia = personaService.getAll()
                    .stream()
                    .map(Personas::getNumeroFamilia)
                    .distinct()
                    .collect(Collectors.toList());
        }

        model.addAttribute("numerosDeFamilia", numerosDeFamilia);
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
}
