package com.TP.ong.ModuloReceta.Presentacion.Recetas;

import com.TP.ong.ModuloReceta.Entidades.DetalleReceta;
import com.TP.ong.ModuloReceta.Entidades.Ingrediente;
import com.TP.ong.ModuloReceta.Entidades.Receta;
import com.TP.ong.ModuloReceta.Servicios.RecetaService;
import com.TP.ong.ModuloReceta.AccesoDAO.IIngredienteDAO;
import com.TP.ong.ModuloReceta.AccesoDAO.IRecetaRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/recetas")
@RequiredArgsConstructor
public class RecetasController {

    private final RecetaService recetaService;
    private final IIngredienteDAO ingredienteDAO;
    private final IRecetaRepo recetaRepo;


    @GetMapping("/nueva")
    public String mostrarFormularioNuevaReceta(Model model) {
        if (!model.containsAttribute("formBean")) {
            RecetaDTO formBean = new RecetaDTO();
            formBean.getIngredientes().add(new DetalleRecetaDTO()); // al menos 1 ingrediente
            model.addAttribute("formBean", formBean);
        }
        return "recetas/crear";
    }


    @PostMapping("/guardar")
    public String guardarReceta(@ModelAttribute("formBean") RecetaDTO formBean, RedirectAttributes redirectAttributes) {
        try {
            Receta receta = new Receta();
            receta.setNombre(formBean.getNombre());
            receta.setDescripcion(formBean.getDescripcion());

            List<DetalleReceta> detalles = formBean.getIngredientes().stream().map(f -> {
                DetalleReceta dr = new DetalleReceta();

                Ingrediente ingrediente = ingredienteDAO.findByNombre(f.getIngredienteNombre())
                        .orElseGet(() -> {
                            Ingrediente nuevo = new Ingrediente();
                            nuevo.setNombre(f.getIngredienteNombre());
                            return ingredienteDAO.save(nuevo);
                        });

                dr.setIngrediente(ingrediente);
                dr.setCantidad(f.getCantidad());
                dr.setCalorias(f.getCalorias());
                dr.setReceta(receta);
                return dr;
            }).collect(Collectors.toList());

            receta.setDetalles(detalles);

            recetaService.crearReceta(receta);

            redirectAttributes.addFlashAttribute("mensaje", "Receta guardada correctamente");
            return "redirect:/recetas/listar";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la receta: " + e.getMessage());
            redirectAttributes.addFlashAttribute("formBean", formBean);
            return "redirect:/recetas/nueva";
        }
    }


    @GetMapping("/editar/{id}")
    public String editarReceta(@PathVariable Long id, Model model) {
        Receta receta = recetaRepo.findById(id).orElse(null);
        if (receta == null) return "redirect:/recetas/listar";

        RecetaDTO formBean = new RecetaDTO();
        formBean.setId(receta.getId());
        formBean.setNombre(receta.getNombre());
        formBean.setDescripcion(receta.getDescripcion());

        receta.getDetalles().forEach(d -> {
            DetalleRecetaDTO detalleDTO = new DetalleRecetaDTO();
            detalleDTO.setIngredienteNombre(d.getIngrediente().getNombre());
            detalleDTO.setCantidad(d.getCantidad());
            detalleDTO.setCalorias(d.getCalorias());
            formBean.getIngredientes().add(detalleDTO);
        });

        model.addAttribute("formBean", formBean);
        return "recetas/modificar";
    }


    @PostMapping("/actualizar")
    public String actualizarReceta(@ModelAttribute("formBean") RecetaDTO formBean, RedirectAttributes redirectAttributes) {
        try {
            Receta existente = recetaRepo.findById(formBean.getId()).orElse(null);
            if (existente == null) {
                redirectAttributes.addFlashAttribute("error", "Receta no encontrada");
                return "redirect:/recetas/listar";
            }


            existente.setNombre(formBean.getNombre());
            existente.setDescripcion(formBean.getDescripcion());


            List<DetalleReceta> nuevosDetalles = formBean.getIngredientes().stream().map(f -> {
                DetalleReceta dr = new DetalleReceta();

                Ingrediente ingrediente = ingredienteDAO.findByNombre(f.getIngredienteNombre())
                        .orElseGet(() -> {
                            Ingrediente nuevo = new Ingrediente();
                            nuevo.setNombre(f.getIngredienteNombre());
                            return ingredienteDAO.save(nuevo);
                        });

                dr.setIngrediente(ingrediente);
                dr.setCantidad(f.getCantidad());
                dr.setCalorias(f.getCalorias());
                dr.setReceta(existente);

                return dr;
            }).collect(Collectors.toList());


            existente.getDetalles().clear();
            existente.getDetalles().addAll(nuevosDetalles);

            recetaRepo.save(existente);

            redirectAttributes.addFlashAttribute("mensaje", "Receta modificada correctamente");
            return "redirect:/recetas/listar";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar la receta: " + e.getMessage());
            redirectAttributes.addFlashAttribute("formBean", formBean);
            return "redirect:/recetas/editar/" + formBean.getId();
        }
    }


    @GetMapping("/listar")
    public String listarRecetas(Model model) {
        List<Receta> recetas = recetaService.listarRecetas();


        List<RecetaConCalorias> recetasConCalorias = recetas.stream()
                .map(r -> new RecetaConCalorias(r, calcularCaloriasTotales(r)))
                .collect(Collectors.toList());

        model.addAttribute("recetas", recetasConCalorias);
        return "recetas/listar";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarReceta(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            recetaService.eliminarReceta(id);
            redirectAttributes.addFlashAttribute("mensaje", "Receta eliminada correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la receta: " + e.getMessage());
        }
        return "redirect:/recetas/listar";
    }

    @GetMapping("/ver/{id}")
    public String verReceta(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Receta receta = recetaRepo.findById(id).orElse(null);
        if (receta == null) {
            redirectAttributes.addFlashAttribute("error", "Receta no encontrada");
            return "redirect:/recetas/listar";
        }

        model.addAttribute("receta", receta);
        model.addAttribute("caloriasTotales", calcularCaloriasTotales(receta));
        return "recetas/ver";
    }

 
    @Data
    public static class RecetaConCalorias {
        private final Receta receta;
        private final int caloriasTotales;
    }

    private int calcularCaloriasTotales(Receta receta) {
        return receta.getDetalles().stream()
                .mapToInt(DetalleReceta::getCalorias)
                .sum();
    }


    @Data
    public static class RecetaDTO {
        private Long id;
        private String nombre;
        private String descripcion;
        private List<DetalleRecetaDTO> ingredientes = new ArrayList<>();
    }

    @Data
    public static class DetalleRecetaDTO {
        private String ingredienteNombre;
        private Double cantidad;
        private Integer calorias;
    }
}
