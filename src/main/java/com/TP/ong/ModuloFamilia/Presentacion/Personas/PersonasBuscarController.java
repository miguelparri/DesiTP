/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.TP.ong.ModuloFamilia.Presentacion.Personas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonasBuscarController {

    @GetMapping("/buscarpersonas")
    public String mostrarFormularioBusqueda(Model model) {
        model.addAttribute("mensajebuscar", "Búsqueda de personas");
        return "familias/buscarpersonas";  // busca familias/buscarpersonas.html
    }
}
