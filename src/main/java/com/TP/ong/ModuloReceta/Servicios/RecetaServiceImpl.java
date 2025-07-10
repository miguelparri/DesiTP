package com.TP.ong.ModuloReceta.Servicios;

import com.TP.ong.ModuloReceta.AccesoDAO.IRecetaRepo;
import com.TP.ong.ModuloReceta.Entidades.*;
import com.TP.ong.ModuloReceta.Excepciones.RecetaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private IRecetaRepo recetaRepo;

    @Override
    public Receta crearReceta(Receta receta) {
        if (recetaRepo.existsByNombre(receta.getNombre())) {
            throw new RecetaException("Ya existe una receta con ese nombre.");
        }

        receta.setActiva(true);

        // Setear referencia a receta en cada detalle (importante)
        if (receta.getDetalles() != null) {
            for (DetalleReceta detalle : receta.getDetalles()) {
                detalle.setReceta(receta);
            }
        }

        return recetaRepo.save(receta);
    }

    @Override
    public void eliminarReceta(Long id) {
        Optional<Receta> recetaOpt = recetaRepo.findById(id);
        if (recetaOpt.isEmpty()) {
            throw new RecetaException("La receta no existe.");
        }

        Receta receta = recetaOpt.get();
        receta.setActiva(false); // baja lógica
        recetaRepo.save(receta);
    }

    @Override
    public Receta modificarReceta(Long id, Receta nueva) {
        Receta receta = recetaRepo.findById(id)
                .orElseThrow(() -> new RecetaException("Receta no encontrada"));

        receta.setDescripcion(nueva.getDescripcion());
        receta.getDetalles().clear(); // Eliminación lógica 

        if (nueva.getDetalles() != null) {
            for (DetalleReceta detalle : nueva.getDetalles()) {
                detalle.setReceta(receta);
                receta.getDetalles().add(detalle);
            }
        }

        return recetaRepo.save(receta);
    }

    @Override
    public List<Receta> listarRecetas() {
        return recetaRepo.findAll()
                .stream()
                .filter(Receta::isActiva)
                .collect(Collectors.toList());
    }

    @Override
    public List<Receta> filtrarPorCalorias(int minCalorias, int maxCalorias) {
        return recetaRepo.findAll()
                .stream()
                .filter(r -> r.isActiva() && calcularCaloriasTotales(r) >= minCalorias && calcularCaloriasTotales(r) <= maxCalorias)
                .collect(Collectors.toList());
    }

    private int calcularCaloriasTotales(Receta receta) {
        return receta.getDetalles().stream()
                .mapToInt(DetalleReceta::getCalorias)
                .sum();
    }
}