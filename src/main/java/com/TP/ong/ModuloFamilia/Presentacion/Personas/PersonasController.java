package com.TP.ong.ModuloFamilia.Presentacion.Personas;

import com.TP.ong.ModuloFamilia.Entidades.Personas;
import com.TP.ong.ModuloFamilia.AccesoDAO.IPersonaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonasController {

    @Autowired
    private IPersonaDao personaDao;

    @GetMapping("/personas")
public String mostrarPersonas(Model model) {
    var personas = personaDao.findAll();
    model.addAttribute("personas", personas);

    // Agregá el formBean para que Thymeleaf no tire error al usar th:object
    model.addAttribute("formBean", new PersonasBuscarForm());

    return "familias/personas";
}

}