package com.TP.ong.ModuloReceta.Presentacion.Recetas;

import com.TP.ong.ModuloReceta.Entidades.DetalleReceta;
import com.TP.ong.ModuloReceta.Entidades.Ingrediente;
import com.TP.ong.ModuloReceta.Entidades.Receta;
import com.TP.ong.ModuloReceta.Servicios.RecetaService;
import com.TP.ong.ModuloReceta.AccesoDAO.IIngredienteDAO;
import com.TP.ong.ModuloReceta.AccesoDAO.IRecetaRepo;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recetas")
@RequiredArgsConstructor
public class RecetasController {

    private final RecetaService recetaService;
    private final IIngredienteDAO ingredienteDAO;
    private final IRecetaRepo recetaRepo;

    // Mostrar formulario de alta
    @GetMapping("/nueva")
    public String mostrarFormularioNuevaReceta(Model model) {
        RecetaDTO recetaForm = new RecetaDTO();
        recetaForm.getIngredientes().add(new DetalleRecetaDTO()); // mínimo un ingrediente

        model.addAttribute("recetaForm", recetaForm);
        model.addAttribute("ingredientesDisponibles", ingredienteDAO.findAll());
        return "recetas/crear";
    }

    // Guardar receta nueva
    @PostMapping("/guardar")
    public String guardarReceta(@ModelAttribute RecetaDTO recetaForm) {
        Receta receta = new Receta();
        receta.setNombre(recetaForm.getNombre());
        receta.setDescripcion(recetaForm.getDescripcion());

        List<DetalleReceta> detalles = recetaForm.getIngredientes().stream().map(f -> {
            DetalleReceta dr = new DetalleReceta();
            dr.setIngrediente(ingredienteDAO.findById(f.getIngredienteId()).orElse(null));
            dr.setCantidad(f.getCantidad());
            dr.setCalorias(f.getCalorias());
            return dr;
        }).toList();

        receta.setDetalles(detalles);
        recetaService.crearReceta(receta);
        return "redirect:/recetas/listar";
    }

    // Listar recetas
    @GetMapping("/listar")
    public String listarRecetas(@ModelAttribute("busqueda") RecetaBuscarForm busqueda, Model model) {
        List<Receta> recetas;

        if ((busqueda.getNombre() != null && !busqueda.getNombre().isEmpty()) ||
            (busqueda.getMinCalorias() != null && busqueda.getMaxCalorias() != null)) {
            int min = busqueda.getMinCalorias() != null ? busqueda.getMinCalorias() : 0;
            int max = busqueda.getMaxCalorias() != null ? busqueda.getMaxCalorias() : Integer.MAX_VALUE;
            recetas = recetaService.filtrarPorCalorias(min, max);
        } else {
            recetas = recetaService.listarRecetas();
        }

        model.addAttribute("recetas", recetas);
        return "recetas/listar";
    }

    // Eliminar (lógica) receta
    @GetMapping("/eliminar/{id}")
    public String eliminarReceta(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
        return "redirect:/recetas/listar";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarReceta(@PathVariable Long id, Model model) {
        Receta receta = recetaRepo.findById(id).orElse(null);
        if (receta == null) return "redirect:/recetas/listar";

        RecetaDTO form = new RecetaDTO();
        form.setId(receta.getId());
        form.setNombre(receta.getNombre()); // read-only
        form.setDescripcion(receta.getDescripcion());

        receta.getDetalles().forEach(d -> {
            DetalleRecetaDTO f = new DetalleRecetaDTO();
            f.setIngredienteId(d.getIngrediente().getId());
            f.setCantidad(d.getCantidad());
            f.setCalorias(d.getCalorias());
            form.getIngredientes().add(f);
        });

        model.addAttribute("recetaForm", form);
        model.addAttribute("ingredientesDisponibles", ingredienteDAO.findAll());
        model.addAttribute("editando", true);
        return "recetas/crear";
    }

    // Guardar edición
    @PostMapping("/actualizar")
    public String actualizarReceta(@ModelAttribute RecetaDTO recetaForm) {
        Receta nueva = new Receta();
        nueva.setDescripcion(recetaForm.getDescripcion());

        List<DetalleReceta> detalles = recetaForm.getIngredientes().stream().map(f -> {
            DetalleReceta dr = new DetalleReceta();
            dr.setIngrediente(ingredienteDAO.findById(f.getIngredienteId()).orElse(null));
            dr.setCantidad(f.getCantidad());
            dr.setCalorias(f.getCalorias());
            return dr;
        }).toList();

        nueva.setDetalles(detalles);
        recetaService.modificarReceta(recetaForm.getId(), nueva);
        return "redirect:/recetas/listar";
    }

    // ----------------------
    // DTOs
    // ----------------------

    @Data
    public static class RecetaDTO {
        private Long id;
        private String nombre;
        private String descripcion;
        private List<DetalleRecetaDTO> ingredientes = new ArrayList<>();
    }

    @Data
    public static class DetalleRecetaDTO {
        private Long ingredienteId;
        private Double cantidad;
        private Integer calorias;
    }

    @Data
    public static class RecetaBuscarForm {
        private String nombre;
        private Integer minCalorias;
        private Integer maxCalorias;
    }
}