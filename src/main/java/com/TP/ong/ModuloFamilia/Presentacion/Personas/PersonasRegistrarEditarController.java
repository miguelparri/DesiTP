/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.TP.ong.ModuloFamilia.Presentacion.Personas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PersonasRegistrarEditarController {

    @GetMapping("/editarpersonas")
    public String mostrarFormularioEditar(Model model) {
        model.addAttribute("mensajeeditar", "Editor de personas");
        return "familias/editarpersonas";  // busca familias/editarpersonas.html
    }
}
