package com.TP.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller



public class ControladorRest {
    @GetMapping("/")
    public String comienzo() {
        return "indice";  // Nombre de la plantilla sin extensión
    }
}
